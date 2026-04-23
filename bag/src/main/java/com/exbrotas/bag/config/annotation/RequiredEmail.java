package com.exbrotas.bag.config.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailValidator.class)
public @interface RequiredEmail {
  public String message() default "";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}