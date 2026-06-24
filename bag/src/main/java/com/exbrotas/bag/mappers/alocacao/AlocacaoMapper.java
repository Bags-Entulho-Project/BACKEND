package com.exbrotas.bag.mappers.alocacao;

import com.exbrotas.bag.dtos.request.alocacao.AlocacaoAtualizarDto;
import com.exbrotas.bag.dtos.request.alocacao.AlocacaoCriarDto;
import com.exbrotas.bag.dtos.response.alocacao.AlocacaoResponseDto;
import com.exbrotas.bag.entities.Alocacao;
import com.exbrotas.bag.enums.AlocacaoStatus;
import com.exbrotas.bag.repositories.projections.AlocacaoProjection;
import java.time.LocalDateTime;


public class AlocacaoMapper {

  public static Alocacao toAlocacao(AlocacaoCriarDto dto) {
    return Alocacao.builder()
        .pessoaId(dto.getPessoaId())
        .bagId(dto.getBagId())
        .status(AlocacaoStatus.alocado)
        .isCancel(false)
        .build();
  }

  public static void updateAlocacao(AlocacaoAtualizarDto dto, Alocacao alocacao) {
    alocacao.setPessoaId(dto.getPessoaId());
    alocacao.setBagId(dto.getBagId());
    alocacao.setStatus(
        dto.getDevolucao() != null ? AlocacaoStatus.devolvida : AlocacaoStatus.alocado);
    alocacao.setDevolucao(dto.getDevolucao());
  }

  public static AlocacaoResponseDto toAlocacaoResponseDto(AlocacaoProjection proj) {
    return AlocacaoResponseDto.builder()
        .id(proj.getId())
        .pessoaId(proj.getPessoaId())
        .bagId(proj.getBagId())
        .devolucao(proj.getDevolucao())
        .entrega(proj.getEntrega())
        .status(proj.getStatus())
        .build();
  }

}
