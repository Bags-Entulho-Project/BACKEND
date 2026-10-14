package com.exbrotas.bag.repository.repositories.pessoa;

import com.exbrotas.bag.entities.pessoa.Pessoa;
import com.exbrotas.bag.repository.projections.pessoa.PessoaCountProjection;
import com.exbrotas.bag.repository.projections.pessoa.PessoaProjection;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

  @Query(
      "Select p.id as id, count(a) as totalAlocacao from Pessoa p join p.alocacao a where a.isCancel = false "
          + "group by p.id")
  Stream<PessoaCountProjection> countTotalAlocacao();

  List<PessoaProjection> findAllProjectedByAndIsCancelFalse();

  @Transactional
  default Map<Integer, Integer> countTotalAlocacaoProjectedBy() {
    return countTotalAlocacao().collect(
        Collectors.toMap(PessoaCountProjection::getId, PessoaCountProjection::getTotalAlocacao));
  }
}