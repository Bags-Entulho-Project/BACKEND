package com.exbrotas.bag.mappers.auth;

import com.exbrotas.bag.dtos.response.auth.LoginResponseDto;
import com.exbrotas.bag.entities.Usuario;

public class AuthMapper {

  public static LoginResponseDto toLoginResponseDto(Usuario usu) {
    return LoginResponseDto.builder()
        .nome(usu.getNome())
        .email(usu.getEmail())
        .isAdmin(usu.getIsAdmin())
        .build();
  }
}
