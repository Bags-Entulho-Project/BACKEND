package com.exbrotas.bag.controller;

import com.exbrotas.bag.dtos.request.user.UsuarioAtualizarDto;
import com.exbrotas.bag.dtos.request.user.UsuarioAtualizarSenhaDto;
import com.exbrotas.bag.dtos.request.user.UsuarioCriarDto;
import com.exbrotas.bag.dtos.response.user.UsuarioGetResponseDto;
import com.exbrotas.bag.dtos.security.SystemUser;
import com.exbrotas.bag.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @Operation(summary = "Pega todos os Usuários", description = "Busca todos os usuários cadastrados "
      + "pelo admin")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retornou todos os usuários cadastrados")
  })
  @GetMapping
  public ResponseEntity<List<UsuarioGetResponseDto>> pegarTodosUsuarios() {
    return ResponseEntity.ok(usuarioService.pegarTodosUsuarios());
  }

  @Operation(summary = "Cria usuário", description =
      "Recebe nome e email do usuário, cria uma senha "
          + "aleatória e envia essa senha para o email do usuário criado")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Usuário foi criado."),
      @ApiResponse(responseCode = "400", description = "Erro de email duplicado")
  })
  @PostMapping
  public ResponseEntity<Void> criar(@RequestBody UsuarioCriarDto dto) {
    usuarioService.criarUsuario(dto);

    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Atualiza informações do usuário", description =
      "Recebe as novas informações do usuário"
          + " busca ele no banco, e verifica se precisa reenviar email de senha")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "O usuário foi atualizado"),
      @ApiResponse(responseCode = "404", description = "O usuário não foi encontrado")
  })
  @PutMapping
  public ResponseEntity<Void> atualizar(@RequestBody UsuarioAtualizarDto dto) {
    usuarioService.atualizarUsuario(dto);

    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Atualiza senha do usuário", description =
      "Recebe a senha e a confirmação da senha "
          + "e criptografa ela para salvar no banco")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "A senha foi atualizada"),
      @ApiResponse(responseCode = "400", description = "As senhas enviadas não batem umas com as outras")
  })
  @PatchMapping
  public ResponseEntity<Void> atualizarSenha(@RequestBody UsuarioAtualizarSenhaDto dto,
      @AuthenticationPrincipal SystemUser user) {
    usuarioService.updatePassword(dto, user);

    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Muda o status do usuário", description = "Ativa ou desativa o usuário selecionado")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Usuário desativado || ativado"),
      @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
  })
  @PatchMapping("change-status/{id}")
  public ResponseEntity<Void> changeStatus(@PathVariable("id") Integer id,
      @AuthenticationPrincipal SystemUser user) {
    usuarioService.changeStatus(id, user);

    return ResponseEntity.noContent().build();
  }
}