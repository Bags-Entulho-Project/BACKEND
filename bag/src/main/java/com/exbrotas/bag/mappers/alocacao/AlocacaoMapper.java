package com.exbrotas.bag.mappers.alocacao;

import com.exbrotas.bag.dtos.request.alocacao.AlocacaoCriarDto;
import com.exbrotas.bag.dtos.response.alocacao.AlocacaoResponseDto;
import com.exbrotas.bag.dtos.security.SystemUser;
import com.exbrotas.bag.entities.alocacao.Alocacao;
import com.exbrotas.bag.enums.AlocacaoStatus;
import com.exbrotas.bag.repository.projections.alocacao.AlocacaoProjection;
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

  public static void updateAlocacao(AlocacaoCriarDto dto, Alocacao alocacao) {
    alocacao.setPessoaId(dto.getPessoaId());
    alocacao.setBagId(dto.getBagId());
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

  public static void devolverAlocacao(SystemUser user, Alocacao alocacao) {
    alocacao.setIsCancel(true);
    alocacao.setDeletedBy(user.id());
    alocacao.getBag().setDisponivel(true);
    alocacao.setDevolucao(LocalDateTime.now());
    alocacao.setStatus(AlocacaoStatus.devolvida);
  }

}
