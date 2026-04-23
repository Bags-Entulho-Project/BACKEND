package com.exbrotas.bag.entities;

import com.exbrotas.bag.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "Imoveis")
public class Imoveis extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "IMO_ID")
  private Integer id;

  @Column(name = "IMO_PES_ID", nullable = false)
  private Integer pessoaId;

  @Column(name = "IMO_IPTU", nullable = false)
  private String iptu;

  @Column(name = "IMO_LOGRADOURO", nullable = false)
  private String logradouro;

  @Column(name = "IMO_NUM", nullable = false)
  private String numero;

  @Column(name = "IMO_COMPLEMENTO")
  private String complemento;

  @Column(name = "IMO_CEP", nullable = false, length = 8)
  private String cep;

  @Column(name = "IMO_BAIRRO", nullable = false)
  private String bairro;

  @Column(name = "IMO_CIDADE", nullable = false)
  private String cidade;

  @Column(name = "IMO_UF", nullable = false, length = 100)
  private String uf;

  @CreatedDate
  @Column(name = "IMO_INCLUSAO", nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "IMO_ALTERACAO")
  private LocalDateTime updatedAt;

  @Column(name = "IMO_CANCEL", nullable = false)
  private Boolean isActive;

  @EqualsAndHashCode.Exclude
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IMO_PESSOA_ID", insertable = false, updatable = false)
  private Pessoa pessoa;
}