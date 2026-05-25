package com.exbrotas.bag.repositories.projections;

public interface BagProjection {
  Integer getId();

  Boolean getDisponivel();

  String getNumero();

  String getObservacao();
}
