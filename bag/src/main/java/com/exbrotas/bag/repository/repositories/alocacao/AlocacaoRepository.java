package com.exbrotas.bag.repository.repositories.alocacao;

import com.exbrotas.bag.entities.alocacao.Alocacao;
import com.exbrotas.bag.repository.projections.alocacao.AlocacaoProjection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlocacaoRepository extends JpaRepository<Alocacao, Integer> {

  Integer countByPessoaIdAndIsCancelFalse(Integer pessoaId);

  Integer countByPessoaIdAndIsCancelFalseAndIdNot(Integer pessoaId, Integer id);

  List<AlocacaoProjection> findAllByIsCancelFalse();
}
