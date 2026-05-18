package com.exbrotas.bag.repositories;

import com.exbrotas.bag.entities.Usuario;
import com.exbrotas.bag.repositories.projections.UsuarioProjection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  Optional<Usuario> findByEmail(String email);

  List<UsuarioProjection> findAllProjectedBy();

  boolean existsByEmail(String email);

  @Modifying
  @Query("Update Usuario Set senha = :senha WHERE id = :id")
  void updatePassword(@Param("senha") String senha, @Param("id") Integer usuarioId);
}