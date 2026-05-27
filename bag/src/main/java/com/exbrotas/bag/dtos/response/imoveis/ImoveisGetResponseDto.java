package com.exbrotas.bag.dtos.response.imoveis;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
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
public class ImoveisGetResponseDto {
  @Schema(description = "Id da residencia")
  private Integer id;

  @Schema(description = "Iptu do residencia")
  private String iptu;

  @Schema(description = "Rua da residencia")
  private String logradouro;

  @Schema(description = "Numero da residencia")
  private String numero;

  @Schema(description = "Complemento da residencia")
  private String complemento;

  @Schema(description = "Cep da residencia")
  private String cep;

  @Schema(description = "Bairro da residencia")
  private String bairro;

  @Schema(description = "Cidade da residencia")
  private String cidade;

  @Schema(description = "Estado do Brasil onde a residencia está")
  private String uf;
}