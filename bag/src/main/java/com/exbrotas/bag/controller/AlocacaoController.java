package com.exbrotas.bag.controller;

import com.exbrotas.bag.dtos.request.alocacao.AlocacaoCriarDto;
import com.exbrotas.bag.dtos.response.alocacao.AlocacaoResponseDto;
import com.exbrotas.bag.dtos.security.SystemUser;
import com.exbrotas.bag.services.alocacao.AlocacaoService;
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
@RequestMapping("api/v1/alocacao")
public class AlocacaoController {

  private final AlocacaoService alocacaoService;

  public AlocacaoController(AlocacaoService alocacaoService) {
    this.alocacaoService = alocacaoService;
  }

  @Operation(summary = "Lista todas as alocações", description = "Busca todas as alocações registradas")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retorna a lista")
  })
  @GetMapping
  public ResponseEntity<List<AlocacaoResponseDto>> listar() {
    return ResponseEntity.ok(alocacaoService.listar());
  }

  @Operation(summary = "Cria uma alocação", description =
      "Registra uma alocação com a bag enviada e a "
          + "pessoa enviada")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Alocação registrada"),
      @ApiResponse(responseCode = "404", description = "Bag não encontrada"),
      @ApiResponse(responseCode = "400", description = "Bag indisponível ou Pessoa ja tem 2 bags em seu nome")
  })
  @PostMapping
  public ResponseEntity<Void> criar(@Valid @RequestBody AlocacaoCriarDto dto) {
    alocacaoService.criar(dto);

    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Atualiza uma alocação", description =
      "Atualiza uma alocação com o os novos dados, e pode mudar o status da alocação")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Alocação atualizada"),
      @ApiResponse(responseCode = "404", description = "Bag ou alocação não encontrada"),
      @ApiResponse(responseCode = "400", description = "Bag indisponível ou Pessoa ja tem 2 bags em seu nome")
  })
  @PatchMapping("{id}")
  public ResponseEntity<Void> atualizar(@Valid @RequestBody AlocacaoCriarDto dto,
      @PathVariable("id") Integer id) {
    alocacaoService.atualizar(dto, id);

    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Devolver Alocação", description = "Faz com que uma alocação especifica seja "
      + "mude seu status de alocada para devolvida")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Alocação devolvida"),
      @ApiResponse(responseCode = "404", description = "Alocação não encontrada")
  })
    @PatchMapping("devolver/{id}")
  public ResponseEntity<Void> devolver(@PathVariable("id") Integer id,
      @AuthenticationPrincipal SystemUser user) {
    alocacaoService.devolver(id, user);

    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Desativa uma alocação", description =
      "Desativa a alocação selecionada")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Alocação desativada"),
      @ApiResponse(responseCode = "404", description = "Alocação não encontrada")
  })
  @PatchMapping("change-status/{id}")
  public ResponseEntity<Void> changeStatus(@PathVariable("id") Integer id, @AuthenticationPrincipal
  SystemUser user) {
    alocacaoService.changeStatus(id, user);

    return ResponseEntity.noContent().build();
  }
}
