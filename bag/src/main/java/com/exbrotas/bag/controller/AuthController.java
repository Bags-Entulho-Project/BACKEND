package com.exbrotas.bag.controller;

import com.exbrotas.bag.dtos.request.auth.LoginDto;
import com.exbrotas.bag.dtos.security.SystemUser;
import com.exbrotas.bag.entities.Usuario;
import com.exbrotas.bag.services.AuthService;
import com.exbrotas.bag.services.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
    UUID refreshToken = jwtService.createRefreshToken(token, usuario.getId());

    this.setCookie(token, "jwt", response, null);
    this.setCookie(refreshToken.toString(), "refreshToken", response, null);

    return ResponseEntity.ok().build();
  }

  @PostMapping("check-session")
  public ResponseEntity<Void> checkSession(@AuthenticationPrincipal SystemUser user) {
    if (user != null) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }

  @PostMapping("refresh")
  public ResponseEntity<Void> refreshToken(HttpServletRequest request,
      HttpServletResponse response) {
    Optional<Cookie> refreshCookie = Stream.of(Optional.ofNullable(request.getCookies())
        .orElse(new Cookie[0])).filter(
        cookie -> cookie.getName().equalsIgnoreCase("refreshToken")
    ).findFirst();

    if (refreshCookie.isPresent()) {
      String jwt = jwtService.remakeToken(UUID.fromString(refreshCookie.get().getValue()));

      if (jwt != null) {
        this.setCookie(jwt, "jwt", response, null);
        return ResponseEntity.ok().build();
      } else {
        SecurityContextHolder.clearContext();

        this.setCookie(null, "jwt", response, 0);
        this.setCookie(null, "refreshToken", response, 0);
      }
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }

  @PostMapping("logout")
  public ResponseEntity<Void> logout(HttpServletResponse response) {
    SecurityContextHolder.clearContext();

    this.setCookie(null, "jwt", response, 0);
    this.setCookie(null, "refreshToken", response, 0);

    return ResponseEntity.noContent().build();
  }

  private void setCookie(String token, String cookieName, HttpServletResponse response,
      Integer maxAge) {
    Cookie cookie = new Cookie(cookieName, token);
    cookie.setPath("/");
    if (maxAge != null) {
      cookie.setMaxAge(maxAge);
    }
    cookie.setHttpOnly(true);
    cookie.setSecure(false);

    response.addCookie(cookie);
  }
}