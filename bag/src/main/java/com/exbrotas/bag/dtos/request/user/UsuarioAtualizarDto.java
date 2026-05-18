package com.exbrotas.bag.dtos.request.user;

import com.exbrotas.bag.config.annotation.RequiredEmail;
import com.exbrotas.bag.config.annotation.RequiredString;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAtualizarDto {

  @NotNull
  @Schema(description = "Id do usuário que vai atualizar")
  private Integer id;

  @RequiredEmail
  @Schema(description = "Novo email que vai ser trocado")
  private String email;

  @RequiredString
  @Schema(description = "Novo nome que vai ser trocado")
  private String nome;

  @NotNull
  @Schema(description = "Caso cadastrante tenha errado o email, colocar como true para reenviar email "
      + "de senha")
  private Boolean reenviarEmail;
}
