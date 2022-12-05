package com.currencyconversion.repository;

import com.currencyconversion.entity.AssetCurrency;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyConverterRepository extends PagingAndSortingRepository<AssetCurrency, Long>, CrudRepository<AssetCurrency, Long> {
  @NotNull
  @Override
  List<AssetCurrency> findAll();

  AssetCurrency getByTimestamp(Long timestamp);
}