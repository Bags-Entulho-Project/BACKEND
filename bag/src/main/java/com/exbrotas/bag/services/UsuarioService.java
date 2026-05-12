package com.exbrotas.bag.services;

import com.exbrotas.bag.dtos.request.UsuarioCreateDto;
import com.exbrotas.bag.entities.Usuario;
import com.exbrotas.bag.listeners.EmailListenerEvent;
import com.exbrotas.bag.mappers.ususario.UsuarioMapper;
import com.exbrotas.bag.repositories.UsuarioRepository;
import com.exbrotas.bag.utils.SenhaUtil;
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

  public void criarUsuario(UsuarioCreateDto dto){
    StringBuilder sb = new StringBuilder(10);
    for (int i = 0; i < 10; i++) {
      int randomIndex = random.nextInt(CHAR_POOL.length());
      sb.append(CHAR_POOL.charAt(randomIndex));
    }
    String senha = SenhaUtil.encode(sb.toString());
    Usuario usuario = UsuarioMapper.mapUsuarioFromDto(dto, senha);
    usuarioRepository.save(usuario);
    publisher.publishEvent(new EmailListenerEvent(this, senha, usuario.getEmail()));
  }
}