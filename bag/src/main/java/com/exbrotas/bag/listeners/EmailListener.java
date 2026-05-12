package com.exbrotas.bag.listeners;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailListener implements ApplicationListener<EmailListenerEvent> {

  @Value("${spring.mail.username}")
  private String sender;

  private final JavaMailSender mailSender;

  public EmailListener(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Async
  @Override
  public void onApplicationEvent(EmailListenerEvent event) {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper;

    try {
      helper = new MimeMessageHelper(message, true, "UTF-8");
      helper.setFrom(sender);
      helper.setTo(event.to);
      helper.setSubject("Senha temporária");
      helper.setText(String.format("Sua senha temporária é %s, redefina ela o mais rápido possível",
          event.senha));
      mailSender.send(message);
      log.info("EMAIL ENVIADO!");
    } catch (MessagingException e) {
      log.error("An error occured: ", e);
      throw new RuntimeException(e);
    }
  }
}
