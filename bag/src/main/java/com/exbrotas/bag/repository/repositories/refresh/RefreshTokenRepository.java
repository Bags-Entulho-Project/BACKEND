package com.exbrotas.bag.repository.repositories.refresh;

import com.exbrotas.bag.entities.refresh.RefreshToken;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

  Boolean existsByUsuarioId(Integer token);

  void deleteByUsuarioId(Integer token);
}
