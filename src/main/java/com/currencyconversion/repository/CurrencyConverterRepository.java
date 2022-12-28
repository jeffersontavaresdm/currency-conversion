package com.currencyconversion.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyConverterRepository
  extends
  CurrencyConverterCRUDRepository,
  CurrencyConverterPageableRepository {}