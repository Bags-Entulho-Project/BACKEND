package com.exbrotas.bag.controller;

import com.exbrotas.bag.repositories.AlocacaoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("apí/v1/alocacao")
public class AlocacaoController {

  private final AlocacaoRepository alocacaoRepository;

  public AlocacaoController(AlocacaoRepository alocacaoRepository) {
    this.alocacaoRepository = alocacaoRepository;
  }
}