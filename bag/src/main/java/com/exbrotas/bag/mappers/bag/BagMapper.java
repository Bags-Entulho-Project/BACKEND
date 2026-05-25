package com.exbrotas.bag.mappers.bag;

import com.exbrotas.bag.dtos.request.bag.BagAtualizarDto;
import com.exbrotas.bag.dtos.request.bag.BagCriarDto;
import com.exbrotas.bag.dtos.response.bag.BagGetResponseDto;
import com.exbrotas.bag.entities.Bag;
import com.exbrotas.bag.repositories.projections.BagProjection;

public class BagMapper {
  public static BagGetResponseDto toBagGetResponseDto(BagProjection projection) {
    return BagGetResponseDto.builder()
        .id(projection.getId())
        .disponivel(projection.getDisponivel())
        .numero(projection.getNumero())
        .observacao(projection.getObservacao())
        .build();
  }

  public static Bag fromDto(BagCriarDto dto){
    return Bag.builder()
        .numero(dto.getNumero())
        .disponivel(dto.getDisponivel())
        .observacao(dto.getObservacao())
        .isCancel(false)
        .build();
  }

  public static void updateBag(BagAtualizarDto dto, Bag bag ){
    bag.setNumero(dto.getNumero());
    bag.setObservacao(dto.getObservacao());
  }
}