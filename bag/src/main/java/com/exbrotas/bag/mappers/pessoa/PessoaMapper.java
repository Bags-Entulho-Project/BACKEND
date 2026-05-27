package com.exbrotas.bag.mappers.pessoa;

import com.exbrotas.bag.dtos.request.pessoa.PessoaCriarDto;
import com.exbrotas.bag.dtos.response.pessoa.PessoaGetResponseDto;
import com.exbrotas.bag.entities.Pessoa;
import com.exbrotas.bag.mappers.imoveis.ImoveisMapper;
import com.exbrotas.bag.repositories.projections.PessoaProjection;

public class PessoaMapper {

  public static Pessoa fromDto(PessoaCriarDto dto){
    return Pessoa.builder()
        .nome(dto.getNome())
        .cpf(dto.getCpf())
        .fone(dto.getFone())
        .celular(dto.getCelular())
        .isCancel(false)
        .build();
  }

  public static PessoaGetResponseDto toDto(PessoaProjection projection){
    return PessoaGetResponseDto.builder()
        .id(projection.getId())
        .nome(projection.getNome())
        .cpf(projection.getCpf())
        .fone(projection.getFone())
        .celular(projection.getCelular())
        .isCancel(projection.getIsCancel())
        .imoveis(projection.getImoveis().stream().map(ImoveisMapper::toDto).toList())
        .build();
  }

}
