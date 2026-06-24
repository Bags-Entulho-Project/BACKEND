package com.exbrotas.bag.repositories;

import com.exbrotas.bag.entities.Alocacao;
import com.exbrotas.bag.repositories.projections.AlocacaoProjection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlocacaoRepository extends JpaRepository<Alocacao, Integer> {
  Integer countByPessoaIdAndIsCancelFalse(Integer pessoaId);

  List<AlocacaoProjection> findAllByIsCancelFalse();
}