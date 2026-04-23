package com.exbrotas.bag.utils;

import jakarta.validation.ConstraintValidatorContext;

public class MensagemValidationUtil {

  public static void mensagemValidation(ConstraintValidatorContext context, String message) {
    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
  }
}