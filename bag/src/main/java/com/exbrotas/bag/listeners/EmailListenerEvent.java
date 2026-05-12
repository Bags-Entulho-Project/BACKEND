package com.exbrotas.bag.listeners;

import org.springframework.context.ApplicationEvent;

public class EmailListenerEvent extends ApplicationEvent{

  public final String senha;
  public final String to;

  public EmailListenerEvent(Object source, String senha, String to) {
    super(source);
    this.senha = senha;
    this.to = to;
  }
}
