package com.exbrotas.bag.config.annotation.impl;

import com.exbrotas.bag.config.annotation.Iptu;
import com.exbrotas.bag.dtos.request.imoveis.ImoveisDto;
import com.exbrotas.bag.dtos.request.pessoa.PessoaCriarDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidateIptu implements ConstraintValidator<Iptu, PessoaCriarDto> {

  @Override
  public boolean isValid(PessoaCriarDto value, ConstraintValidatorContext context) {
    List<String> original = value.getImoveis().stream().map(ImoveisDto::getIptu).toList();
    Set<String> set = value.getImoveis().stream().map(ImoveisDto::getIptu)
        .collect(Collectors.toSet());
    return set.size() == original.size();
  }
}