package com.exbrotas.bag.services;

import com.exbrotas.bag.config.exceptionHandler.exceptions.MyBadRequestException;
import com.exbrotas.bag.dtos.request.imoveis.ImoveisCriarDto;
import com.exbrotas.bag.dtos.request.pessoa.PessoaCriarDto;
import com.exbrotas.bag.dtos.response.pessoa.PessoaGetResponseDto;
import com.exbrotas.bag.entities.Imoveis;
import com.exbrotas.bag.entities.Pessoa;
import com.exbrotas.bag.mappers.imoveis.ImoveisMapper;
import com.exbrotas.bag.mappers.pessoa.PessoaMapper;
import com.exbrotas.bag.repositories.ImoveisRepository;
import com.exbrotas.bag.repositories.PessoaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

  private final PessoaRepository pessoaRepository;
  private final ImoveisRepository imoveisRepository;

  public PessoaService(PessoaRepository pessoaRepository, ImoveisRepository imoveisRepository) {
    this.pessoaRepository = pessoaRepository;
    this.imoveisRepository = imoveisRepository;
  }

  public List<PessoaGetResponseDto> listar() {
    return pessoaRepository.findAllProjectedBy().stream().map(PessoaMapper::toDto).toList();
  }

  public void criar(PessoaCriarDto dto) {
    if (imoveisRepository.existsByIptuIn(
        dto.getImoveis().stream().map(ImoveisCriarDto::getIptu).toList())) {
      throw new MyBadRequestException("Não é possível registra dois Iptus iguais");
    }

    Pessoa pessoa = PessoaMapper.fromDto(dto);
    pessoaRepository.save(pessoa);

    List<Imoveis> imoveis = dto.getImoveis().stream()
        .map((imo) -> ImoveisMapper.fromDto(imo, pessoa.getId())).toList();
    imoveisRepository.saveAll(imoveis);
  }
}