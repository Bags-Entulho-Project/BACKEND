package com.exbrotas.bag.dtos.request.pessoa;

import com.exbrotas.bag.dtos.request.imoveis.ImoveisDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
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
public class PessoaAtualizarDto extends PessoaCriarDto {
  @NotNull
  private Integer id;

  @Valid
  private List<ImoveisDto> imoveis;
}