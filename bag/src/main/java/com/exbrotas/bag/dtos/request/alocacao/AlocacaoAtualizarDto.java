package com.exbrotas.bag.dtos.request.alocacao;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AlocacaoAtualizarDto extends AlocacaoCriarDto {

  @Schema(description = "Data da devolução da bag")
  private LocalDateTime devolucao;
}
