package com.exbrotas.bag.dtos.request.alocacao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AlocacaoCriarDto {

  @Schema(description = "Id da pessoa que vai pegar emprestado uma bag")
  @NotNull
  private Integer pessoaId;

  @Schema(description = "Id da bag que vai ser emprestada")
  @NotNull
  private Integer bagId;
}