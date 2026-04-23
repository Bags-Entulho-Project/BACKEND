package com.exbrotas.bag.config.exceptionHandler;

import com.exbrotas.bag.config.exceptionHandler.exceptions.MyBadRequestException;
import com.exbrotas.bag.config.exceptionHandler.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerAdviser {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
    log.error("Ocorreu um erro: ", ex);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(MyBadRequestException.class)
  public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
    log.error("Ocorreu um erro: ", ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }
}