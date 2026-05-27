package com.exbrotas.bag.controller;

import com.exbrotas.bag.dtos.request.pessoa.PessoaCriarDto;
import com.exbrotas.bag.dtos.response.pessoa.PessoaGetResponseDto;
import com.exbrotas.bag.entities.Pessoa;
import com.exbrotas.bag.services.PessoaService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/pessoa")
public class PessoaController {

  private final PessoaService pessoaService;

  public PessoaController(PessoaService pessoaService) {
    this.pessoaService = pessoaService;
  }

  @GetMapping
  public ResponseEntity<List<PessoaGetResponseDto>> listar() {
    return ResponseEntity.ok(pessoaService.listar());
  }

  @PostMapping
  public ResponseEntity<Void> criar(@RequestBody PessoaCriarDto dto) {
    pessoaService.criar(dto);

    return ResponseEntity.noContent().build();
  }
}