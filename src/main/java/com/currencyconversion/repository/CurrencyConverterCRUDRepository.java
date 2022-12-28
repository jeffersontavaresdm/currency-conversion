package com.currencyconversion.repository;

import com.currencyconversion.entity.AssetCurrency;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CurrencyConverterCRUDRepository extends CrudRepository<AssetCurrency, Long> {

  @NotNull
  @Override
  List<AssetCurrency> findAll();

  AssetCurrency getByName(String name);

  AssetCurrency findByName(String currencyType);

  AssetCurrency findByCodeAndCodeIn(String currencyType1, List<String> codein);
}
