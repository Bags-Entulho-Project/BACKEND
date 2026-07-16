package com.exbrotas.bag.repositories;

import com.exbrotas.bag.entities.Bag;
import com.exbrotas.bag.repositories.projections.BagProjection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BagRepository extends JpaRepository<Bag, Integer> {

  Boolean existsByNumero(String numero);

  List<BagProjection> findAllProjectedBy();
}