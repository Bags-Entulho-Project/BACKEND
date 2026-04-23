package com.exbrotas.bag.entities;

import com.exbrotas.bag.entities.base.BaseEntity;
import com.exbrotas.bag.enums.AlocacaoStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
@Entity
@Table(name = "Alocacao")
public class Alocacao extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LOC_ID")
  private Integer id;

  @Column(name = "LOC_PES_ID", nullable = false)
  private Integer pessoaId;

  @Column(name = "LOC_BAG_ID", nullable = false)
  private Integer bagId;

  @Column(name = "LOC_DATA_DEVOLUÇÃO", nullable = false)
  private LocalDateTime devolucao;

  @Column(name = "LOC_DATA_ENTREGA")
  private LocalDateTime entrega;

  @Enumerated(EnumType.STRING)
  @Column(name = "LOC_STATUS", nullable = false)
  private AlocacaoStatus status;

  @CreatedDate
  @Column(name = "LOC_INCLUSAO", nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "LOC_ALTERACAO")
  private LocalDateTime updatedAt;

  @Column(name = "LOC_CANCEL", nullable = false)
  private Boolean isActive;

  @EqualsAndHashCode.Exclude
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "LOC_PES_ID", insertable = false, updatable = false)
  private Pessoa pessoa;

  @EqualsAndHashCode.Exclude
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "LOC_BAG_ID", insertable = false, updatable = false)
  private Bag bag;
}