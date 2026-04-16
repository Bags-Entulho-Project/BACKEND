package com.exbrotas.bag.config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

  @Value("${jwt.key.private}")
  private RSAPrivateKey privateKey;

  @Value("${jwt.key.public}")
  private RSAPublicKey publicKey;

  @Bean
  public JwtBuilder jwtBuilder() {
    return Jwts.builder().signWith(privateKey);
  }
}
