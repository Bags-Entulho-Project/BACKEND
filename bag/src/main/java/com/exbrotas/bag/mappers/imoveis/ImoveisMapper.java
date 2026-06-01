package com.exbrotas.bag.mappers.imoveis;

import com.exbrotas.bag.dtos.request.imoveis.ImoveisAtualizarDto;
import com.exbrotas.bag.dtos.request.imoveis.ImoveisCriarDto;
import com.exbrotas.bag.dtos.response.imoveis.ImoveisGetResponseDto;
import com.exbrotas.bag.entities.Imoveis;
import com.exbrotas.bag.repositories.projections.ImoveisProjection;

public class ImoveisMapper {

  public static Imoveis fromDto(ImoveisCriarDto dto, Integer pessoaId) {
    return Imoveis.builder()
        .pessoaId(pessoaId)
        .iptu(dto.getIptu())
        .logradouro(dto.getLogradouro())
        .numero(dto.getNumero())
        .complemento(dto.getComplemento())
        .cep(dto.getCep())
        .bairro(dto.getBairro())
        .cidade(dto.getCidade())
        .uf(dto.getUf())
        .isCancel(false)
        .build();
  }

  public static ImoveisGetResponseDto toDto(ImoveisProjection projection) {
    return ImoveisGetResponseDto.builder()
        .id(projection.getId())
        .iptu(projection.getIptu())
        .logradouro(projection.getLogradouro())
        .numero(projection.getNumero())
        .complemento(projection.getComplemento())
        .cep(projection.getCep())
        .bairro(projection.getBairro())
        .cidade(projection.getCidade())
        .uf(projection.getUf())
        .build();
  }

  public static void atualizarImovel(Imoveis imoveis, ImoveisAtualizarDto dto) {
    imoveis.setIptu(dto.getIptu());
    imoveis.setLogradouro(dto.getLogradouro());
    imoveis.setNumero(dto.getNumero());
    imoveis.setComplemento(dto.getComplemento());
    imoveis.setCep(dto.getCep());
    imoveis.setBairro(dto.getBairro());
    imoveis.setCidade(dto.getCidade());
    imoveis.setUf(dto.getUf());
  }
}