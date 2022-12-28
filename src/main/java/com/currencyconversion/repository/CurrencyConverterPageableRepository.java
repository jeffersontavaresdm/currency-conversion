package com.currencyconversion.repository;

import com.currencyconversion.entity.AssetCurrency;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CurrencyConverterPageableRepository extends PagingAndSortingRepository<AssetCurrency, Long> {}
