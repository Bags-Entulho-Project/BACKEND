package com.exbrotas.bag.services;

import com.exbrotas.bag.config.exceptionHandler.exceptions.MyBadRequestException;
import com.exbrotas.bag.config.exceptionHandler.exceptions.NotFoundException;
import com.exbrotas.bag.dtos.LoginDto;
import com.exbrotas.bag.entities.Usuario;
import com.exbrotas.bag.repositories.UsuarioRepository;
import com.exbrotas.bag.utils.SenhaUtil;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final UsuarioRepository usuarioRepository;

  public AuthService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public Usuario login(LoginDto dto) {
    Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
        .orElseThrow(() -> new NotFoundException("Usuario nao encontrado"));

    if (SenhaUtil.match(dto.getSenha(), usuario.getSenha())) {
      return usuario;
    }
    throw new MyBadRequestException("Senha Incorreta");
  }
}