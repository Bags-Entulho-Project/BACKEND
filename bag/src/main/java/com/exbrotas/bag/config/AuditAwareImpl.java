package com.exbrotas.bag.config;

import com.exbrotas.bag.dtos.security.SystemUser;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditAwareImpl implements AuditorAware<Integer> {

  public Optional<Integer> getCurrentAuditor() {
    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
        .map(authentication -> {
          if (authentication.getPrincipal() instanceof SystemUser user){
            return user.id();
          }
          return null;
        });
  }
}
