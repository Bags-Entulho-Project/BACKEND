package com.exbrotas.bag.dtos.request.imoveis;

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
public class ImoveisAtualizarDto extends ImoveisCriarDto{
  private Integer id;

}
