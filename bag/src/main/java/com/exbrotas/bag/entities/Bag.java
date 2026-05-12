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
@Table(name = "bag")
@Entity
public class Bag extends BaseEntity {

  @Id
  @Column(name = "BAG_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "BAG_DISPO", nullable = false)
  private Boolean disponivel;

  @Column(name = "BAG_NUM", nullable = false)
  private String numero;

  @Column(name = "BAG_OBSERVACAO")
  private String observacao;

  @CreatedDate
  @Column(name = "BAG_INCLUSAO", nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "BAG_ALTERACAO")
  private LocalDateTime updatedAt;

  @Column(name = "BAG_CANCEL", nullable = false)
  private Boolean isCancel;
}