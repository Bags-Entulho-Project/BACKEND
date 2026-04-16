package com.exbrotas.bag.entities.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity {

  @Column(name = "LOG_USU_ALT")
  private Integer updatedBy;

  @Column(name = "LOG_USU_INC")
  private Integer createdBy;

  @Column(name = "LOG_USU_CANCEL")
  private Integer deletedBy;
}