package com.currencyconversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CurrencyConversionApplication {

  public static void main(String[] args) {
    SpringApplication.run(CurrencyConversionApplication.class, args);
  }
}