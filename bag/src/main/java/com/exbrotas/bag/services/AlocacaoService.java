package com.exbrotas.bag.services;

import com.exbrotas.bag.repositories.AlocacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class AlocacaoService {

  private final AlocacaoRepository alocacaoRepository;

  public AlocacaoService(AlocacaoRepository alocacaoRepository) {
    this.alocacaoRepository = alocacaoRepository;
  }
}