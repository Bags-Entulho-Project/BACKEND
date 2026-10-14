package com.exbrotas.bag.services.alocacao;

import com.exbrotas.bag.config.exceptionHandler.exceptions.MyBadRequestException;
import com.exbrotas.bag.config.exceptionHandler.exceptions.NotFoundException;
import com.exbrotas.bag.dtos.request.alocacao.AlocacaoCriarDto;
import com.exbrotas.bag.dtos.response.alocacao.AlocacaoResponseDto;
import com.exbrotas.bag.dtos.security.SystemUser;
import com.exbrotas.bag.entities.alocacao.Alocacao;
import com.exbrotas.bag.entities.bag.Bag;
import com.exbrotas.bag.entities.pessoa.Pessoa;
import com.exbrotas.bag.mappers.alocacao.AlocacaoMapper;
import com.exbrotas.bag.repository.repositories.alocacao.AlocacaoRepository;
import com.exbrotas.bag.repository.repositories.bag.BagRepository;
import com.exbrotas.bag.repository.repositories.pessoa.PessoaRepository;
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
    Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
        .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
    Integer totalAlocacao = alocacaoRepository.countByPessoaIdAndIsCancelFalse(dto.getPessoaId());

    if (pessoa.getIsCancel()) {
      throw new MyBadRequestException("Pessoa está inativa!");
    }

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

  public void atualizar(AlocacaoCriarDto dto, Integer id) {
    Alocacao alocacao = alocacaoRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Alocação não encontrada"));
    Bag bag = bagRepository.findById(dto.getBagId())
        .orElseThrow(() -> new NotFoundException("Bag não encontrada"));

    if (pessoaRepository.existsById(dto.getPessoaId())) {
      Integer totalAlocacao = alocacaoRepository
          .countByPessoaIdAndIsCancelFalseAndIdNot(dto.getPessoaId(), id);
      if (totalAlocacao < 2) {
        if (!alocacao.getBagId().equals(dto.getBagId())) {
          if (bag.getDisponivel()) {
            Bag bagAnterior = bagRepository.findById(alocacao.getBagId())
                .orElseThrow(() -> new NotFoundException("Bag anterior não encontrada"));
            bagAnterior.setDisponivel(true);
            bag.setDisponivel(false);
            bagRepository.save(bagAnterior);
          } else {
            throw new MyBadRequestException("Bag indisponível!");
          }
        }
        AlocacaoMapper.updateAlocacao(dto, alocacao);
        alocacaoRepository.save(alocacao);
        bagRepository.save(bag);
      } else {
        throw new MyBadRequestException("Pessoa ja possui 2 alocações em seu nome");
      }
    } else {
      throw new NotFoundException("Pessoa não encontrada");
    }
  }

  public void devolver(Integer id, SystemUser user) {
    Alocacao alocacao = alocacaoRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Alocação não encontrada"));

    AlocacaoMapper.devolverAlocacao(user, alocacao);

    alocacaoRepository.save(alocacao);
    bagRepository.save(alocacao.getBag());
  }

  public void changeStatus(Integer id, SystemUser user) {
    Alocacao alocacao = alocacaoRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Alocação não encontrada"));
    alocacao.setIsCancel(!alocacao.getIsCancel());
    alocacao.setDeletedBy(user.id());
    alocacaoRepository.save(alocacao);

    if (alocacao.getIsCancel() && alocacao.getDevolucao() == null) {
      Bag bag = bagRepository.findById(alocacao.getBagId())
          .orElseThrow(() -> new NotFoundException("Bag não encontrada"));
      bag.setDisponivel(true);
      bagRepository.save(bag);
    }
  }
}
