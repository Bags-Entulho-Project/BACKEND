package com.exbrotas.bag.config;

import com.exbrotas.bag.config.cors.CorsConfig;
import com.exbrotas.bag.config.filter.JwtAuthFilter;
import com.exbrotas.bag.services.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  private final JwtService jwtService;
  private final CorsConfig corsConfiguration;

  public SecurityConfig(JwtService jwtService, CorsConfig corsConfiguration) {
    this.jwtService = jwtService;
    this.corsConfiguration = corsConfiguration;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
            .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
            .anyRequest().authenticated()
        )
        .cors(c -> c.configurationSource(corsConfiguration))
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    http.addFilterBefore(new JwtAuthFilter(jwtService), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}