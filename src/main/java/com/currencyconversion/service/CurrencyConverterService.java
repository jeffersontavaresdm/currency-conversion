package com.currencyconversion.service;

import com.currencyconversion.api.CurrencyConverterAPI;
import com.currencyconversion.entity.AssetCurrency;
import com.currencyconversion.entity.dto.AssetCurrencyDTO;
import com.currencyconversion.entity.dto.AssetTypes;
import com.currencyconversion.repository.CurrencyConverterRepository;
import com.currencyconversion.utils.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyConverterService {

  private final Logger logger = LoggerUtils.loggerFor(this);

  private final CurrencyConverterAPI api;
  private final CurrencyConverterRepository repository;

  public CurrencyConverterService(CurrencyConverterAPI api, CurrencyConverterRepository repository) {
    this.api = api;
    this.repository = repository;
  }

  public AssetTypes types() {
    Map<String, String> map = new HashMap<>();

    repository.findAll().forEach(asset -> map.put(asset.getCode(), asset.getCodeName()));

    return new AssetTypes(map);
  }

  public ResponseEntity<List<AssetCurrencyDTO>> all() {
    var assets = repository
      .findAll()
      .stream()
      .map(AssetCurrency::assetCurrencyHandler)
      .toList();

    return ResponseEntity.status(HttpStatus.OK).body(assets);
  }

  public ResponseEntity<AssetCurrencyDTO> convert(String currencyType1, String currencyType2) {
    Response<Map<String, AssetCurrency>> response = null;
    try {
      response = api.convert(currencyType1, currencyType2).execute();
    } catch (IOException exception) {
      var error = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
      logger.error("Error when execute the request. Error: {}", error);
    }

    if (response != null && response.isSuccessful()) {
      Map<String, AssetCurrency> assetCurrencyMap = response.body();

      if (assetCurrencyMap != null && !assetCurrencyMap.isEmpty()) {
        var asset = assetCurrencyMap
          .values()
          .stream()
          .findFirst()
          .get();

        return ResponseEntity.status(HttpStatus.OK).body(asset.assetCurrencyHandler());
      }
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }
}
