package com.exbrotas.bag.mappers.ususario;

import com.exbrotas.bag.dtos.request.UsuarioCreateDto;
import com.exbrotas.bag.entities.Usuario;

public class UsuarioMapper {

  public static Usuario mapUsuarioFromDto(UsuarioCreateDto dto, String senha){
    return Usuario.builder()
        .nome(dto.getNome())
        .email(dto.getEmail())
        .senha(senha)
        .build();
  }
}
