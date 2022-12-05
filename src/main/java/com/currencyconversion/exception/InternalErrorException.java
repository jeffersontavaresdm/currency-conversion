package com.currencyconversion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalErrorException extends BaseException {

  public InternalErrorException() {
    super("internal.error");
  }

  public InternalErrorException(String error) {
    super(error);
  }
}