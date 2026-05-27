package com.exbrotas.bag.repositories;

import com.exbrotas.bag.entities.Pessoa;
import com.exbrotas.bag.repositories.projections.PessoaProjection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
  List<PessoaProjection> findAllProjectedBy();
}