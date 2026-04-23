package com.exbrotas.bag.repositories;

import com.exbrotas.bag.entities.Bag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BagRepository extends JpaRepository<Bag, Integer> {

}