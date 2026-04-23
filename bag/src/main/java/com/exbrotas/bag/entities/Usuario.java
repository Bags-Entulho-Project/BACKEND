package com.exbrotas.bag.entities;

import com.exbrotas.bag.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Usuario")
public class Usuario extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USU_ID")
  private Integer id;

  @Column(name = "USU_SENHA", nullable = false)
  private String senha;

  @Column(name = "USU_NOME", nullable = false)
  private String nome;

  @Column(name = "USU_EMAIl", nullable = false)
  private String email;

  @Column(name = "USU_ADMIN")
  private Boolean isAdmin;

  @CreatedDate
  @Column(name = "USU_DATA_INCLUSAO", nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "USU_DATA_ALTERACAO")
  private LocalDateTime updatedAt;

  @Column(name = "USU_CANCEL", nullable = false)
  private Boolean isActive;
}
