package com.exbrotas.bag.dtos.response.usuario;

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
public class UsuarioGetResponseDto {

  private Integer id;

  private String nome;

  private String email;

  private Boolean cancelado;

}
