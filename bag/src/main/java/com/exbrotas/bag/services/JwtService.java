package com.exbrotas.bag.services;

import com.exbrotas.bag.dtos.security.SystemUser;
import com.exbrotas.bag.entities.RefreshToken;
import com.exbrotas.bag.entities.Usuario;
import com.exbrotas.bag.repositories.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParserBuilder;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  @Value("${jwt.expiration.time}")
  private Long expirationTimeJwt;

  @Value("${refresh.expiration.time}")
  private Long refreshExpirationTime;

  private final JwtBuilder jwtBuilder;
  private final JwtParserBuilder jwtParser;
  private final RefreshTokenRepository refreshTokenRepository;

  public JwtService(JwtBuilder jwtBuilder, JwtParserBuilder jwtParser,
      RefreshTokenRepository refreshTokenRepository) {
    this.jwtBuilder = jwtBuilder;
    this.jwtParser = jwtParser;
    this.refreshTokenRepository = refreshTokenRepository;
  }

  public String generateToken(Usuario usuario) {
    Map<String, Object> claims = new HashMap<>() {
      {
        put("id", usuario.getId());
        put("nome", usuario.getNome());
        put("email", usuario.getEmail());
        put("isAdmin", usuario.getIsAdmin());
      }
    };

    return jwtBuilder
        .claims(claims)
        .issuer("arthur")
        .issuedAt(new Date())
        .expiration(Date.from(Instant.now().plusMillis(expirationTimeJwt)))
        .compact();
  }

  public UUID createRefreshToken(String token, Integer usuarioId) {
    RefreshToken refreshToken = RefreshToken.builder()
        .usuarioId(usuarioId)
        .tokenJwt(token)
        .tempoExpiracao(Instant.now().plusSeconds(refreshExpirationTime))
        .build();

    refreshTokenRepository.save(refreshToken);
    return refreshToken.getId();
  }

  public String remakeToken(UUID refreshTokenId) {
    RefreshToken refreshToken = refreshTokenRepository.findById(refreshTokenId).orElse(null);

    String token = null;
    if (refreshToken != null && !refreshToken.isExpired()) {
      token = this.generateToken(refreshToken.getUsuario());
      refreshToken.setTokenJwt(token);
    } else {
      refreshTokenRepository.deleteById(refreshTokenId);
    }

    return token;
  }

  public Authentication setAuthentication(String token) {
    Jws<Claims> jwt = jwtParser.build().parseSignedClaims(token);
    Integer id = jwt.getPayload().get("id", Integer.class);
    String nome = jwt.getPayload().get("nome", String.class);
    String email = jwt.getPayload().get("email", String.class);
    Boolean isAdmin = jwt.getPayload().get("isAdmin", Boolean.class);

    return new UsernamePasswordAuthenticationToken(new SystemUser(id, nome, email, isAdmin), null,
        null);
  }
}