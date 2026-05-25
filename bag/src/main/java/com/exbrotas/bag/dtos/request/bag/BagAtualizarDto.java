package com.exbrotas.bag.dtos.request.bag;

import com.exbrotas.bag.config.annotation.RequiredString;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
@Builder
public class BagAtualizarDto{

  @Schema(description = "Id da bag")
  private Integer id;

  @RequiredString
  @Schema(description = "Etiqueta/identificador da bag")
  private String numero;

  @Schema(description = "Observação sobre a bag")
  private String observacao;
}
