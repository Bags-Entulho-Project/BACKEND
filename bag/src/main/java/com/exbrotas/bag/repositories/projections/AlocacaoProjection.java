package com.exbrotas.bag.repositories.projections;

import com.exbrotas.bag.enums.AlocacaoStatus;
import java.time.LocalDateTime;

public interface AlocacaoProjection {

  Integer getId();

  Integer getPessoaId();

  Integer getBagId();

  LocalDateTime getDevolucao();

  LocalDateTime getEntrega();

  AlocacaoStatus getStatus();
}