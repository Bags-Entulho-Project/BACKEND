package com.exbrotas.bag.repositories;

import com.exbrotas.bag.entities.Alocacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlocacaoRepository extends JpaRepository<Alocacao, Integer> {

}
