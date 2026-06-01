package com.exbrotas.bag.dtos.request.imoveis;

import com.exbrotas.bag.config.annotation.RequiredString;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
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
public class ImoveisCriarDto {

  @RequiredString
  @Schema(description = "Iptu da pessoa")
  private String iptu;

  @RequiredString
  @Schema(description = "Rua da residencia")
  private String logradouro;

  @RequiredString
  @Schema(description = "Numero da residencia")
  private String numero;

  @Schema(description = "Informações adicionais sobre a residencia")
  private String complemento;

  @RequiredString
  @Schema(description = "Cep da rua da residencia. Caso a cidade não tenha cep por rua, colocar "
      + "cep da cidade")
  private String cep;

  @RequiredString
  @Schema(description = "Bairro da residencia")
  private String bairro;

  @RequiredString
  @Schema(description = "Cidade da residencia")
  private String cidade;

  @RequiredString
  @Schema(description = "Estado do Brasil que a residencia está")
  private String uf;
}