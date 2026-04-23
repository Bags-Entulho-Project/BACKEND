package com.exbrotas.bag.config.filter;

import com.exbrotas.bag.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  public JwtAuthFilter(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    Optional<Cookie> jwtCookie = Arrays.stream(request.getCookies())
        .filter(cookie -> cookie.getName().equals("jwt")).findFirst();

    if (jwtCookie.isPresent()) {
      try {
        SecurityContextHolder.getContext()
            .setAuthentication(jwtService.setAuthentication(jwtCookie.get().getValue()));

      } catch (Exception e) {
        SecurityContextHolder.clearContext();
      }
    }
    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String url = request.getServletPath();

    return url.contains("/api/auth") && url.contains("/check-session");
  }
}
