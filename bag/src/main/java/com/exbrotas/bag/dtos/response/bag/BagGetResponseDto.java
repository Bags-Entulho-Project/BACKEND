package com.exbrotas.bag.dtos.response.bag;

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
public class BagGetResponseDto {

  @Schema(description = "Id interno da bag")
  private Integer id;

  @Schema(description = "Bag está disponível ou não")
  private Boolean disponivel;

  @Schema(description = "Etiqueta/identificador da bag")
  private String numero;

  @Schema(description = "Observação sobre a bag")
  private String observacao;
}