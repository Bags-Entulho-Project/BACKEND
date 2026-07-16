package com.exbrotas.bag.config.cors;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;

@Configuration
public class CorsConfig implements CorsConfigurationSource {

  @Value("${spring.origins}")
  private String origins;

  @Override
  public @Nullable CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowedOrigins(List.of(origins.split(",")));
    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
    corsConfiguration.setAllowedHeaders(List.of("*"));
    corsConfiguration.setAllowCredentials(true);

    return corsConfiguration;
  }
}
