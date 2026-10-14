package com.exbrotas.bag.repository.repositories.bag;

import com.exbrotas.bag.entities.bag.Bag;
import com.exbrotas.bag.repository.projections.bag.BagProjection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BagRepository extends JpaRepository<Bag, Integer> {

  Boolean existsByNumero(String numero);

  List<BagProjection> findAllProjectedByAndIsCancelFalse();
}