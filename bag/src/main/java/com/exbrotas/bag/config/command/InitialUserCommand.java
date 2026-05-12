package com.exbrotas.bag.config.command;

import com.exbrotas.bag.entities.Usuario;
import com.exbrotas.bag.repositories.UsuarioRepository;
import com.exbrotas.bag.utils.SenhaUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialUserCommand implements CommandLineRunner {

  @Value("${spring.admin.email}")
  private String adminEmail;

  @Value("${spring.admin.senha}")
  private String adminSenha;

  private final UsuarioRepository usuarioRepository;

  public InitialUserCommand(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Usuario usuario = usuarioRepository.findByEmail(adminEmail).orElse(null);
    if (usuario == null) {
      usuario = Usuario.builder()
          .nome("admin")
          .email(adminEmail)
          .senha(SenhaUtil.encode(adminSenha))
          .isCancel(false)
          .isAdmin(true)
          .build();
      usuarioRepository.save(usuario);
    }
  }
}
