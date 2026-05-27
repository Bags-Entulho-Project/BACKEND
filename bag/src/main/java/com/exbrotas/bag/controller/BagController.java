package com.exbrotas.bag.controller;

import com.exbrotas.bag.dtos.request.bag.BagAtualizarDto;
import com.exbrotas.bag.dtos.request.bag.BagCriarDto;
import com.exbrotas.bag.dtos.response.bag.BagGetResponseDto;
import com.exbrotas.bag.dtos.security.SystemUser;
import com.exbrotas.bag.services.BagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/bag")
public class BagController {

  private final BagService bagService;

  public BagController(BagService bagService) {
    this.bagService = bagService;
  }

  @Operation(summary = "Pega todas as bags", description = "Busca todas as bags Cadastradas pelo admin")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retornou todas as bags cadastradas")
  })
  @GetMapping
  public ResponseEntity<List<BagGetResponseDto>> listar() {
    return ResponseEntity.ok(bagService.listar());
  }

  @Operation(summary = "Cadastra uma bag", description = "Pegas as informações fornecidas e registra "
      + "uma bag com essa informações")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Bag foi criada"),
      @ApiResponse(responseCode = "400", description = "Numero da bag duplicado")
  })
  public ResponseEntity<Void> criar(@RequestBody BagCriarDto dto) {
    bagService.criar(dto);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Atualiza a bag", description = "Atualiza os dados da bag com as informações enviadas")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Bag atualizada"),
      @ApiResponse(responseCode = "400", description = "Numero da bag duplicado"),
      @ApiResponse(responseCode = "404", description = "Bag não encontrada")
  })
  @PatchMapping()
  public ResponseEntity<Void> atualizar(@RequestBody BagAtualizarDto dto) {
    bagService.atualizar(dto);

    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Muda a disponibilidade da bag", description = "Muda o status de disponibilidade "
      + "da bag para disponivel ou indisponivel")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Disponibilidade alterada"),
      @ApiResponse(responseCode = "404", description = "Bag não encontrada")
  })
  @PatchMapping("{id}")
  public ResponseEntity<Void> atualizarDisponibilidade(@PathVariable("id") Integer id) {
    bagService.atualizarDispo(id);
    return ResponseEntity.noContent().build();
  }
  
  @Operation(summary = "Muda o status da bag", description = "Ativa ou desativa a bag selecionada")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Status Atualizado"),
      @ApiResponse(responseCode = "404", description = "Bag não encontrada")
  })
  @PatchMapping("change-status/{id}")
  public ResponseEntity<Void> changeStatus(@PathVariable("id") Integer id, @AuthenticationPrincipal
      SystemUser user) {
    bagService.changeStatus(id, user);

    return  ResponseEntity.noContent().build();
  }
}