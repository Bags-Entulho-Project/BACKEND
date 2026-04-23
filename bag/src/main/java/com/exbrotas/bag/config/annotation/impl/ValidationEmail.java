package com.exbrotas.bag.config.annotation.impl;

import com.exbrotas.bag.config.annotation.RequiredEmail;
import com.exbrotas.bag.utils.MensagemValidationUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidationEmail implements ConstraintValidator<RequiredEmail, String> {

  private static final String EMAILREGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if(value == null){
      MensagemValidationUtil.mensagemValidation(context, "Mensagem não pode ser nula.");
      return false;
    }

    if (StringUtils.isBlank(value)){
      MensagemValidationUtil.mensagemValidation(context, "Mensagem não pode estar vazia.");
      return false;
    }
    String email = value.trim();

    return email.matches(EMAILREGEX);
  }
}