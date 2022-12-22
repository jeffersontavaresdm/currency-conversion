package com.currencyconversion.exception.handler;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CurrencyConverterControllerAdvice {

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<ProblemDetail> exceptionHandler(Exception exception) {
    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()));
  }

  @ExceptionHandler(value = {IncorrectResultSizeDataAccessException.class})
  public ResponseEntity<ProblemDetail> exceptionHandler(IncorrectResultSizeDataAccessException exception) {
    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()));
  }
}