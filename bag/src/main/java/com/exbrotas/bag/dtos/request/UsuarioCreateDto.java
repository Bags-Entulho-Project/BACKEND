package com.exbrotas.bag.dtos.request;

import com.exbrotas.bag.config.annotation.RequiredEmail;
import com.exbrotas.bag.config.annotation.RequiredString;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioCreateDto {

  @Schema(description = "Nome da pessoa que esta sendo cadastrada")
  @RequiredString
  private String nome;

  @Schema(description = "Email da pessoa que esta sendo cadastrada")
  @RequiredEmail
  private String email;
}