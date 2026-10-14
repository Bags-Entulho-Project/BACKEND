package com.exbrotas.bag.repository.projections.pessoa;

import com.exbrotas.bag.repository.projections.imoveis.ImoveisProjection;
import java.util.List;

public interface PessoaProjection {

  Integer getId();

  String getNome();

  String getCpf();

  String getFone();

  String getCelular();

  List<ImoveisProjection> getImoveis();
}