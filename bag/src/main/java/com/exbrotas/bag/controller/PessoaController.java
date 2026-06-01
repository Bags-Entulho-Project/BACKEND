package com.exbrotas.bag.controller;

import com.exbrotas.bag.dtos.request.pessoa.PessoaAtualizarDto;
import com.exbrotas.bag.dtos.request.pessoa.PessoaCriarDto;
import com.exbrotas.bag.dtos.response.pessoa.PessoaGetResponseDto;
import com.exbrotas.bag.dtos.security.SystemUser;
import com.exbrotas.bag.entities.Pessoa;
import com.exbrotas.bag.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

  @Operation(summary = "Lista todas as pessoas", description = "Busca todas as pessoas no banco e "
      + "seus imóveis")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Lista todos as pessoas"),
  })
  @GetMapping
  public ResponseEntity<List<PessoaGetResponseDto>> listar() {
    return ResponseEntity.ok(pessoaService.listar());
  }

  @Operation(summary = "Cria uma pessoa", description = "Com as informações recebidas, cria a pessoa e "
      + "todos os imóveis citados")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Lista todos as pessoas"),
      @ApiResponse(responseCode = "400", description = "Iptus iguais")
  })
  @PostMapping
  public ResponseEntity<Void> criar(@Valid @RequestBody PessoaCriarDto dto) {
    pessoaService.criar(dto);

    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Atualiza a pessoa", description = "Atualiza a pessoa e seus imóveis com as informações "
      + "recebidas")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Lista todos as pessoas"),
      @ApiResponse(responseCode = "400", description = "Pessoa não encontrada")
  })
  @PatchMapping
  public ResponseEntity<Void> atualizar(@Valid @RequestBody PessoaAtualizarDto dto) {
    pessoaService.atualizar(dto);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Muda o status da pessoa", description = "Busca a pessoa e muda seu estado e de "
      + "seus imóveis para o opostos do atual")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Lista todos as pessoas"),
      @ApiResponse(responseCode = "400", description = "Pessoa não encontrada")
  })
  @PatchMapping("change-status/{id}")
  public ResponseEntity<Void> changeStatus(@PathVariable("id") Integer id,
      @AuthenticationPrincipal SystemUser user) {
    pessoaService.changeStatus(id, user);
    return ResponseEntity.noContent().build();
  }
}