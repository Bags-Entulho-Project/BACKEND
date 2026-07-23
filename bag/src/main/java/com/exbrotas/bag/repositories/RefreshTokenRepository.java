package com.exbrotas.bag.repositories;

import com.exbrotas.bag.entities.RefreshToken;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Boolean existsByUsuarioId(Integer token);

    void deleteByUsuarioId(Integer token);
}
