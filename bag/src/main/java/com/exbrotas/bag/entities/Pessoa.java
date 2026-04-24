package com.exbrotas.bag.entities;

import com.exbrotas.bag.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@Table(name = "Pessoa")
@Entity
public class Pessoa extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PES_ID")
  private Integer id;

  @Column(name = "PES_NOME", nullable = false)
  private String nome;

  @Column(name = "PES_CPF", nullable = false, length = 14)
  private String cpf;

  @Column(name = "PES_FONE", length = 30)
  private String fone;

  @Column(name = "PES_CELULAR", length = 11)
  private String celular;

  @CreatedDate
  @Column(name = "PES_INCLUSAO", nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "PES_ALTERACAO")
  private LocalDateTime updatedAt;

  @Column(name = "PES_CANCEL", nullable = false)
  private Boolean isCancel;

  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "pessoa")
  private List<Alocacao> alocacao;
}