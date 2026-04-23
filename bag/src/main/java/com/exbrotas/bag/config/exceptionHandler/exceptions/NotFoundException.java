package com.exbrotas.bag.config.exceptionHandler.exceptions;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
        super(message);
    }
}