package com.exbrotas.bag.repositories;

import com.exbrotas.bag.entities.Imoveis;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImoveisRepository extends JpaRepository<Imoveis, Integer> {
  Boolean existsByIptuIn(List<String> iptu);
}