package com.currencyconversion.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class CurrencyConverterAPIConfig {

  @Value("${api.baseUrl}")
  private String baseUrl;

  @Bean
  public CurrencyConverterAPI generateClient() {
    var mapper = new ObjectMapper()
      .findAndRegisterModules()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    return new Retrofit
      .Builder()
      .baseUrl(baseUrl)
      .addConverterFactory(JacksonConverterFactory.create(mapper))
      .build()
      .create(CurrencyConverterAPI.class);
  }
}