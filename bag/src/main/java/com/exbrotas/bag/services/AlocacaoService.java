package com.exbrotas.bag.services;

import com.exbrotas.bag.config.exceptionHandler.exceptions.MyBadRequestException;
import com.exbrotas.bag.config.exceptionHandler.exceptions.NotFoundException;
import com.exbrotas.bag.dtos.request.alocacao.AlocacaoAtualizarDto;
import com.exbrotas.bag.dtos.request.alocacao.AlocacaoCriarDto;
import com.exbrotas.bag.dtos.response.alocacao.AlocacaoResponseDto;
import com.exbrotas.bag.dtos.security.SystemUser;
import com.exbrotas.bag.entities.Alocacao;
import com.exbrotas.bag.entities.Bag;
import com.exbrotas.bag.mappers.alocacao.AlocacaoMapper;
import com.exbrotas.bag.repositories.AlocacaoRepository;
import com.exbrotas.bag.repositories.BagRepository;
import com.exbrotas.bag.repositories.PessoaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AlocacaoService {

  private final AlocacaoRepository alocacaoRepository;
  private final PessoaRepository pessoaRepository;
  private final BagRepository bagRepository;

  public AlocacaoService(AlocacaoRepository alocacaoRepository, PessoaRepository pessoaRepository,
      BagRepository bagRepository) {
    this.alocacaoRepository = alocacaoRepository;
    this.pessoaRepository = pessoaRepository;
    this.bagRepository = bagRepository;
  }

  public List<AlocacaoResponseDto> listar() {
    return alocacaoRepository.findAllByIsCancelFalse().stream()
        .map(AlocacaoMapper::toAlocacaoResponseDto).toList();
  }

  public void criar(AlocacaoCriarDto dto) {
    Bag bag = bagRepository.findById(dto.getBagId())
        .orElseThrow(() -> new NotFoundException("Bag não encontrada"));
    Integer totalAlocacao = alocacaoRepository.countByPessoaIdAndIsCancelFalse(dto.getPessoaId());

    if (bag.getDisponivel() && totalAlocacao < 2) {
      Alocacao alocacao = AlocacaoMapper.toAlocacao(dto);
      bag.setDisponivel(false);
      alocacaoRepository.save(alocacao);
      bagRepository.save(bag);
    } else if (!bag.getDisponivel()) {
      throw new MyBadRequestException("Bag indisponível!");
    } else {
      throw new MyBadRequestException("Pessoa ja possui 2 alocações em seu nome");
    }
  }

  public void atualizar(AlocacaoAtualizarDto dto, Integer id) {
    Bag bag = bagRepository.findById(dto.getBagId())
        .orElseThrow(() -> new NotFoundException("Bag não encontrada"));
    Integer totalAlocacao = alocacaoRepository.countByPessoaIdAndIsCancelFalse(dto.getPessoaId());

    if (bag.getDisponivel() && totalAlocacao < 2) {
      Alocacao alocacao = alocacaoRepository.findById(id)
          .orElseThrow(() -> new NotFoundException("Alocação não encontrada"));
      AlocacaoMapper.updateAlocacao(dto, alocacao);
      bag.setDisponivel(dto.getDevolucao() != null);
      alocacaoRepository.save(alocacao);
    } else if (!bag.getDisponivel()) {
      throw new MyBadRequestException("Bag indisponível!");
    } else {
      throw new MyBadRequestException("Pessoa ja possui 2 alocações em seu nome");
    }
  }

  public void changeStatus(Integer id, SystemUser user) {
    Alocacao alocacao = alocacaoRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Alocação não encontrada"));
    alocacao.setIsCancel(!alocacao.getIsCancel());
    alocacao.setDeletedBy(user.id());
    alocacaoRepository.save(alocacao);
  }
}