package com.exbrotas.bag.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "refresh_token")
public class RefreshToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TOK_ID")
  private Long id;

  @Column(name = "TOK_TOKEN_JWT")
  private String tokenJwt;

  @Column(name = "TOK_TEMPO_EXPIRACAO")
  private LocalDateTime tempoExpiracao;

  @Column(name = "TOK_USUARIO_ID")
  private Integer usuarioId;

  @EqualsAndHashCode.Exclude
  @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.MERGE,
      CascadeType.PERSIST})
  @JoinColumn(name = "TOK_USUARIO_ID", insertable = false, updatable = false)
  private Usuario usuario;
}