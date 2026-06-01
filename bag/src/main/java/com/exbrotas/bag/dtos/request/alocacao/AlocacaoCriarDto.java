package com.exbrotas.bag.dtos.request.alocacao;

import com.exbrotas.bag.enums.AlocacaoStatus;
import java.time.LocalDateTime;
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
public class AlocacaoCriarDto {
  private Integer pessoaId;

  private Integer bagId;

  private LocalDateTime entrega;

  private AlocacaoStatus status;
}