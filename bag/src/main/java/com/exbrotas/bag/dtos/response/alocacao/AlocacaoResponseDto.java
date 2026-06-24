package com.exbrotas.bag.dtos.response.alocacao;

import com.exbrotas.bag.enums.AlocacaoStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
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
public class AlocacaoResponseDto {

  @Schema(description = "Id da alocação")
  private Integer id;

  @Schema(description = "Id da pessoa que pegou a bag")
  private Integer pessoaId;

  @Schema(description = "Id da bag emprestada")
  private Integer bagId;

  @Schema(description = "Data da devolução da bag")
  private LocalDateTime devolucao;

  @Schema(description = "Data de empréstimo da bag")
  private LocalDateTime entrega;

  @Schema(description = "Status da alocação, como ALOCADO ou DEVOLVIDO")
  private AlocacaoStatus status;
}