package com.exbrotas.bag.repositories.projections;

import java.util.List;

public interface PessoaProjection {
  Integer getId();

  String getNome();

  String getCpf();

  String getFone();

  String getCelular();

  Boolean getIsCancel();

  List<ImoveisProjection> getImoveis();
}