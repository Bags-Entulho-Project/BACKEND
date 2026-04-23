package com.exbrotas.bag.config.annotation.impl;

import com.exbrotas.bag.config.annotation.RequiredString;
import com.exbrotas.bag.utils.MensagemValidationUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.internal.util.StringHelper;

public class ValidString implements ConstraintValidator<RequiredString, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    if(StringHelper.isEmpty(value)) {
      MensagemValidationUtil.mensagemValidation(context, "Esse campo não pode estar em branco");
      return false;
    }

    return true;
  }
}