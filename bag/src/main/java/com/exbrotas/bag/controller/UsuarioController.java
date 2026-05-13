package com.exbrotas.bag.controller;

import com.exbrotas.bag.dtos.request.UsuarioCreateDto;
import com.exbrotas.bag.services.UsuarioService;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Void> createUser(@RequestBody UsuarioCreateDto dto) {
    usuarioService.criarUsuario(dto);

    return ResponseEntity.ok().build();
  }
}
