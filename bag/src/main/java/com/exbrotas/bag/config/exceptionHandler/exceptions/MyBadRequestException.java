package com.exbrotas.bag.config.exceptionHandler.exceptions;

public class MyBadRequestException extends RuntimeException {

  public MyBadRequestException(String message) {
    super(message);
  }
}