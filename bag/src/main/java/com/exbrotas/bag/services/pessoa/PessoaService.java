package com.exbrotas.bag.services.pessoa;

import com.exbrotas.bag.config.exceptionHandler.exceptions.MyBadRequestException;
import com.exbrotas.bag.config.exceptionHandler.exceptions.NotFoundException;
import com.exbrotas.bag.dtos.request.imoveis.ImoveisDto;
import com.exbrotas.bag.dtos.request.pessoa.PessoaAtualizarDto;
import com.exbrotas.bag.dtos.request.pessoa.PessoaCriarDto;
import com.exbrotas.bag.dtos.response.pessoa.PessoaGetResponseDto;
import com.exbrotas.bag.dtos.security.SystemUser;
import com.exbrotas.bag.entities.imoveis.Imoveis;
import com.exbrotas.bag.entities.pessoa.Pessoa;
import com.exbrotas.bag.mappers.imoveis.ImoveisMapper;
import com.exbrotas.bag.mappers.pessoa.PessoaMapper;
import com.exbrotas.bag.repository.projections.pessoa.PessoaProjection;
import com.exbrotas.bag.repository.repositories.alocacao.AlocacaoRepository;
import com.exbrotas.bag.repository.repositories.imovel.ImoveisRepository;
import com.exbrotas.bag.repository.repositories.pessoa.PessoaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

  private final PessoaRepository pessoaRepository;
  private final ImoveisRepository imoveisRepository;
  private final AlocacaoRepository alocacaoRepository;

  public PessoaService(PessoaRepository pessoaRepository, ImoveisRepository imoveisRepository,
      AlocacaoRepository alocacaoRepository) {
    this.pessoaRepository = pessoaRepository;
    this.imoveisRepository = imoveisRepository;
    this.alocacaoRepository = alocacaoRepository;
  }

  public List<PessoaGetResponseDto> listar() {
    List<PessoaProjection> pessoas = pessoaRepository.findAllProjectedByAndIsCancelFalse();
    List<PessoaGetResponseDto> dtos = new ArrayList<>();
    Map<Integer, Integer> map = pessoaRepository.countTotalAlocacaoProjectedBy();
    pessoas.forEach(pessoaProjection -> dtos.add(
        PessoaMapper.toDto(pessoaProjection, map.getOrDefault(pessoaProjection.getId(), 0))));

    return dtos;
  }

  public void criar(PessoaCriarDto dto) {
    if (imoveisRepository.existsByIptuIn(
        dto.getImoveis().stream().map(ImoveisDto::getIptu).toList())) {
      throw new MyBadRequestException("Não é possível registra dois Iptus iguais");
    }

    Pessoa pessoa = PessoaMapper.fromDto(dto);
    pessoaRepository.save(pessoa);

    List<Imoveis> imoveis = dto.getImoveis().stream()
        .map((imo) -> ImoveisMapper.fromDto(imo, pessoa.getId())).toList();
    imoveisRepository.saveAll(imoveis);
  }

  public void atualizar(PessoaAtualizarDto dto) {
    Pessoa pessoa = pessoaRepository.findById(dto.getId())
        .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));

    Map<Integer, ImoveisDto> imoveisDto = dto.getImoveis().stream()
        .collect(Collectors.toMap(ImoveisDto::getId, imoveis -> imoveis));

    List<Imoveis> imoveisRemover = new ArrayList<>();
    List<Imoveis> imoveisAtualizar = new ArrayList<>();

    pessoa.getImoveis().forEach(imoveis -> {
      if (!imoveisDto.containsKey(imoveis.getId())) {
        imoveisRemover.add(imoveis);
      } else {
        ImoveisMapper.atualizarImovel(imoveis, imoveisDto.get(imoveis.getId()));
        imoveisAtualizar.add(imoveis);
      }
    });
    pessoa.getImoveis().removeAll(imoveisRemover);

    dto.getImoveis().forEach(imoveis -> {
      if (imoveis.getId() == null) {
        imoveisAtualizar.add(ImoveisMapper.fromDto(imoveis, pessoa.getId()));
      }
    });
    imoveisRepository.deleteAll(imoveisRemover);
    imoveisRepository.saveAll(imoveisAtualizar);
  }

  public void changeStatus(Integer id, SystemUser user) {
    Pessoa pessoa = pessoaRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));

    pessoa.setIsCancel(!pessoa.getIsCancel());
    pessoa.setDeletedBy(user.id());

    pessoa.getImoveis().forEach(imoveis -> {
      imoveis.setIsCancel(!imoveis.getIsCancel());
      imoveis.setDeletedBy(user.id());
    });
    pessoaRepository.save(pessoa);
    imoveisRepository.saveAll(pessoa.getImoveis());
  }
}
