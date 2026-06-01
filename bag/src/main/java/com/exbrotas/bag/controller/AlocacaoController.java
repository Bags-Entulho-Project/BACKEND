package com.exbrotas.bag.controller;

import com.exbrotas.bag.entities.Alocacao;
import com.exbrotas.bag.repositories.AlocacaoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("apí/v1/alocacao")
public class AlocacaoController {

  private final AlocacaoRepository alocacaoRepository;

  public AlocacaoController(AlocacaoRepository alocacaoRepository) {
    this.alocacaoRepository = alocacaoRepository;
  }

  @PostMapping
  public ResponseEntity<Void> criar(@Valid @RequestBody Alocacao dto) {

    return ResponseEntity.noContent().build();
  }
}