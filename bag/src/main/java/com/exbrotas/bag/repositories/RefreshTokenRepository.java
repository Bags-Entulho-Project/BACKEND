package com.exbrotas.bag.repositories;

import com.exbrotas.bag.entities.RefreshToken;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

}
