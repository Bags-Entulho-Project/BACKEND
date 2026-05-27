package com.exbrotas.bag.dtos.response.pessoa;

import com.exbrotas.bag.dtos.response.imoveis.ImoveisGetResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
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
public class PessoaGetResponseDto {
  @Schema(description = "Id da pessoa")
  private Integer id;

  @Schema(description = "Nome da pessoa")
  private String nome;

  @Schema(description = "Cpf da pessoa")
  private String cpf;

  @Schema(description = "Telefone fixo da pessoa")
  private String fone;

  @Schema(description = "Celular da pessoa")
  private String celular;

  @Schema(description = "Estado da pessoa no sistema")
  private Boolean isCancel;

  @Schema(description = "Imóveis que a pessoa tem cadastrado em seu nome")
  private List<ImoveisGetResponseDto> imoveis;
}