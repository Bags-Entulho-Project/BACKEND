package com.exbrotas.bag.dtos.request;

import com.exbrotas.bag.config.annotation.RequiredEmail;
import com.exbrotas.bag.config.annotation.RequiredString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
  @RequiredEmail
  private String email;

  @RequiredString
  private String senha;
}