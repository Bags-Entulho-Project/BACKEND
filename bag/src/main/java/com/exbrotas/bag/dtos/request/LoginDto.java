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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

  @Schema(description = "Email valido para login")
  @RequiredEmail
  private String email;

  @Schema(description = "Senha do usuário")
  @RequiredString
  private String senha;
}