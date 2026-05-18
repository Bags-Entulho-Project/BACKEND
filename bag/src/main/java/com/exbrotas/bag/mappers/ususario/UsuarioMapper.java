package com.exbrotas.bag.mappers.ususario;

import com.exbrotas.bag.dtos.request.user.UsuarioAtualizarDto;
import com.exbrotas.bag.dtos.request.user.UsuarioCriarDto;
import com.exbrotas.bag.dtos.response.user.UsuarioResponseDto;
import com.exbrotas.bag.entities.Usuario;
import com.exbrotas.bag.repositories.projections.UsuarioProjection;

public class UsuarioMapper {

  public static Usuario mapUsuarioFromDto(UsuarioCriarDto dto, String senha) {
    return Usuario.builder()
        .nome(dto.getNome())
        .email(dto.getEmail())
        .senha(senha)
        .isAdmin(false)
        .isCancel(false)
        .build();
  }

  public static void atualizarInfoUsuario(UsuarioAtualizarDto dto, Usuario usu) {
    usu.setNome(dto.getNome());
    usu.setEmail(dto.getEmail());
  }

  public static UsuarioResponseDto mapUsuarioParaResponseDto(UsuarioProjection usu) {
    return UsuarioResponseDto.builder()
        .id(usu.getId())
        .nome(usu.getNome())
        .email(usu.getEmail())
        .cancelado(usu.getIsCancel())
        .build();
  }
}
