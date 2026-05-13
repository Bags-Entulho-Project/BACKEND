package com.exbrotas.bag.services;

import com.exbrotas.bag.config.exceptionHandler.exceptions.MyBadRequestException;
import com.exbrotas.bag.dtos.request.user.UsuarioAtualizarSenhaDto;
import com.exbrotas.bag.dtos.request.user.UsuarioCriarDto;
import com.exbrotas.bag.dtos.security.SystemUser;
import com.exbrotas.bag.entities.Usuario;
import com.exbrotas.bag.listeners.EmailListenerEvent;
import com.exbrotas.bag.mappers.ususario.UsuarioMapper;
import com.exbrotas.bag.repositories.UsuarioRepository;
import com.exbrotas.bag.utils.SenhaUtil;
import jakarta.transaction.Transactional;
import java.security.SecureRandom;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
  private static final SecureRandom random = new SecureRandom();
  private final UsuarioRepository usuarioRepository;
  private final ApplicationEventPublisher publisher;


  public UsuarioService(UsuarioRepository usuarioRepository, ApplicationEventPublisher publisher) {
    this.usuarioRepository = usuarioRepository;
    this.publisher = publisher;
  }

  public void criarUsuario(UsuarioCriarDto dto){
    if(usuarioRepository.existsByEmail(dto.getEmail())){
      throw new MyBadRequestException("Já existe uma pessoa com esse email");
    }

    StringBuilder sb = new StringBuilder(10);
    for (int i = 0; i < 10; i++) {
      int randomIndex = random.nextInt(CHAR_POOL.length());
      sb.append(CHAR_POOL.charAt(randomIndex));
    }
    String senha = sb.toString();
    Usuario usuario = UsuarioMapper.mapUsuarioFromDto(dto, SenhaUtil.encode(senha));
    publisher.publishEvent(new EmailListenerEvent(this, senha, usuario.getEmail()));
    usuarioRepository.save(usuario);
  }

  @Transactional(rollbackOn = Exception.class)
  public void updatePassword(UsuarioAtualizarSenhaDto dto, SystemUser user){
    if(!dto.getSenha().equals(dto.getConfirmarSenha())){
      throw new MyBadRequestException("Senhas não batem");
    }

    usuarioRepository.updatePassword(SenhaUtil.encode(dto.getSenha()), user.id());
  }
}