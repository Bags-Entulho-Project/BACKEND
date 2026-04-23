package com.exbrotas.bag.repositories;

import com.exbrotas.bag.entities.Imoveis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImoveisRepository extends JpaRepository<Imoveis, Integer> {

}