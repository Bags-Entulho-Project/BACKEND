package com.exbrotas.bag.mappers.ususario;

import com.exbrotas.bag.dtos.request.user.UsuarioCriarDto;
import com.exbrotas.bag.entities.Usuario;

public class UsuarioMapper {

  public static Usuario mapUsuarioFromDto(UsuarioCriarDto dto, String senha){
    return Usuario.builder()
        .nome(dto.getNome())
        .email(dto.getEmail())
        .senha(senha)
        .isAdmin(false)
        .isCancel(false)
        .build();
  }
}
