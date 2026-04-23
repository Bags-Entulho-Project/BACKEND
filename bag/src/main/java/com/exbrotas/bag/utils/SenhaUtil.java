package com.exbrotas.bag.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtil {

  private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public static String encode (String senha) {
    return encoder.encode(senha);
  }

  public static boolean match(String senha, String senhaCriptografada) {
    return encoder.matches(senha, senhaCriptografada);
  }
}