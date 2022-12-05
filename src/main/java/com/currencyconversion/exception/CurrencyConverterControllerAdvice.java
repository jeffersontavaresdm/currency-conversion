package com.currencyconversion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CurrencyConverterControllerAdvice {

  @ExceptionHandler(value = {RuntimeException.class})
  public ResponseEntity<ProblemDetail> exceptionHandler(RuntimeException exception) {
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage()));
  }
}