package com.exbrotas.bag.dtos.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UsuarioResponseDto {

  private Integer id;

  private String nome;

  private String email;

  private Boolean cancelado;

}
