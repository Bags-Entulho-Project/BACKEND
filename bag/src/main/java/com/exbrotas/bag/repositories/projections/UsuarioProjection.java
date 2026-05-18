package com.exbrotas.bag.repositories.projections;

public interface UsuarioProjection {
  Integer getId();

  String getNome();

  String getEmail();

  Boolean getIsCancel();
}
