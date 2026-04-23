package com.exbrotas.bag.controller;

import com.exbrotas.bag.config.filter.JwtAuthFilter;
import com.exbrotas.bag.dtos.LoginDto;
import com.exbrotas.bag.entities.Usuario;
import com.exbrotas.bag.services.AuthService;
import com.exbrotas.bag.services.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

  private final AuthService authService;
  private final JwtService jwtService;

  public AuthController(AuthService authService, JwtService jwtService) {
    this.authService = authService;
    this.jwtService = jwtService;
  }

  @PostMapping("login")
  public ResponseEntity<Void> login(@RequestBody LoginDto dto, HttpServletResponse response) {
    Usuario usuario = authService.login(dto);

    String token = jwtService.generateToken(usuario);

    this.setCookie(token, "jwt", response);

    return ResponseEntity.ok().build();
  }


  private void setCookie(String token, String cookieName, HttpServletResponse response) {
    Cookie cookie = new Cookie(cookieName, token);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setSecure(false);

    response.addCookie(cookie);
  }

}