package com.exbrotas.bag.dtos.request.pessoa;

import com.exbrotas.bag.config.annotation.Iptu;
import com.exbrotas.bag.config.annotation.RequiredString;
import com.exbrotas.bag.dtos.request.imoveis.ImoveisCriarDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Iptu
public class PessoaCriarDto {
  @RequiredString
  @Schema(description = "Nome da pessoa")
  private String nome;

  @CPF
  @Schema(description = "Cpf da pessoa")
  private String cpf;

  @Schema(description = "Telefone fixo da pessoa")
  private String fone;

  @RequiredString
  @Schema(description = "Celular da pessoa")
  private String celular;

  @Valid
  private List<ImoveisCriarDto> imoveis;
}
