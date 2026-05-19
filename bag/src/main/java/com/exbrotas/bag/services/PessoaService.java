package com.exbrotas.bag.services;

import com.exbrotas.bag.repositories.ImoveisRepository;
import com.exbrotas.bag.repositories.PessoaRepository;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

  private final PessoaRepository pessoaRepository;
  private final ImoveisRepository imoveisRepository;

  public PessoaService(PessoaRepository pessoaRepository, ImoveisRepository imoveisRepository) {
    this.pessoaRepository = pessoaRepository;
    this.imoveisRepository = imoveisRepository;
  }
}