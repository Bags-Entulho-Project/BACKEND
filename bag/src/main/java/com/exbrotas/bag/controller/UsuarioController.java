package com.exbrotas.bag.controller;

import com.exbrotas.bag.dtos.request.user.UsuarioAtualizarSenhaDto;
import com.exbrotas.bag.dtos.request.user.UsuarioCriarDto;
import com.exbrotas.bag.dtos.security.SystemUser;
import com.exbrotas.bag.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

  @PostMapping
  public ResponseEntity<Void> criarUsuario(@RequestBody UsuarioCriarDto dto) {
    usuarioService.criarUsuario(dto);

    return ResponseEntity.ok().build();
  }

  @PatchMapping
  public ResponseEntity<Void> atualizarSenha(@RequestBody UsuarioAtualizarSenhaDto dto,
      @AuthenticationPrincipal SystemUser user) {
    usuarioService.updatePassword(dto, user);

    return ResponseEntity.noContent().build();
  }
}
