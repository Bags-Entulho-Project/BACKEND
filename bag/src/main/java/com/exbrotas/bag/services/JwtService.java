package com.exbrotas.bag.services;

import com.exbrotas.bag.dtos.security.SystemUser;
import com.exbrotas.bag.entities.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParserBuilder;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  @Value("${jwt.expiration.time}")
  private Long expirationTime;

  private final JwtBuilder jwtBuilder;
  private final JwtParserBuilder jwtParser;

  public JwtService(JwtBuilder jwtBuilder, JwtParserBuilder jwtParser) {
    this.jwtBuilder = jwtBuilder;
    this.jwtParser = jwtParser;
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
        .expiration(Date.from(Instant.now().plusMillis(expirationTime)))
        .compact();
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