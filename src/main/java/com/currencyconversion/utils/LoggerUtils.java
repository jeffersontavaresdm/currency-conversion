package com.currencyconversion.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {

  public static Logger loggerFor(Object object) {
    return LoggerFactory.getLogger(object.getClass());
  }
}
