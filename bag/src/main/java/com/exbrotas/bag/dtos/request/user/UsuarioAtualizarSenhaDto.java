package com.exbrotas.bag.dtos.request.user;

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
public class UsuarioAtualizarSenhaDto {

  @Schema(description = "Senha para a qual vai mudar")
  @RequiredString
  private String senha;

  @Schema(description = "Confirmação da senha")
  @RequiredString
  private String confirmarSenha;
      ;

}
