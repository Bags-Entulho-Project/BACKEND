package com.exbrotas.bag.controller;

import com.exbrotas.bag.dtos.request.pessoa.PessoaAtualizarDto;
import com.exbrotas.bag.dtos.request.pessoa.PessoaCriarDto;
import com.exbrotas.bag.dtos.response.pessoa.PessoaGetResponseDto;
import com.exbrotas.bag.dtos.security.SystemUser;
import com.exbrotas.bag.entities.Pessoa;
import com.exbrotas.bag.services.PessoaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public ResponseEntity<Void> criar(@Valid @RequestBody PessoaCriarDto dto) {
    pessoaService.criar(dto);

    return ResponseEntity.noContent().build();
  }

  @PatchMapping
  public ResponseEntity<Void> atualizar(@Valid @RequestBody PessoaAtualizarDto dto) {
    pessoaService.atualizar(dto);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("change-status/{id}")
  public ResponseEntity<Void> changeStatus(@PathVariable("id") Integer id,
      @AuthenticationPrincipal SystemUser user) {
    pessoaService.changeStatus(id, user);
    return ResponseEntity.noContent().build();
  }
}