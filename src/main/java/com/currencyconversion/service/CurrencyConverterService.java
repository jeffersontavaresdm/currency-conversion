package com.currencyconversion.service;

import com.currencyconversion.api.CurrencyConverterAPI;
import com.currencyconversion.entity.AssetCurrency;
import com.currencyconversion.entity.dto.AssetCurrencyDTO;
import com.currencyconversion.entity.dto.AssetTypes;
import com.currencyconversion.entity.enuns.AssetType;
import com.currencyconversion.repository.CurrencyConverterRepository;
import com.currencyconversion.utils.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
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
    List<String> assetTypes = repository
      .findAll()
      .stream()
      .map(asset -> asset.getCode().concat("/").concat(AssetType.valueOf(asset.getCode()).value()))
      .toList();

    logger.info("Currency Types count: {}", assetTypes.size());

    return new AssetTypes(assetTypes);
    //    Map<String, String> assets = new HashMap<>();
    //
    //    repository.findAll().forEach(asset -> assets.put(asset.getCode(), AssetType.valueOf(asset.getCode()).value
    //    ()));
    //
    //    return new AssetTypes(assets);
  }

  public ResponseEntity<List<AssetCurrencyDTO>> all() {
    var assets = repository
      .findAll()
      .stream()
      .sorted()
      .map(AssetCurrency::toDTO)
      .toList();

    return ResponseEntity.status(HttpStatus.OK).body(assets);
  }

  public ResponseEntity<AssetCurrencyDTO> convert(AssetType currencyType1, AssetType currencyType2) {
    Response<Map<String, AssetCurrency>> response = null;

    try {
      response = api.convert(currencyType1.name(), currencyType2.name()).execute();
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

        var currency = repository.getByName(asset.getName());

        AssetCurrency assetCurrency;

        if (currency != null) {
          assetCurrency = currency.copy(
            asset.getCode(),
            asset.getCodeIn(),
            asset.getName(),
            asset.getHigh(),
            asset.getLow(),
            asset.getSaleValue(),
            asset.getPercentageChange(),
            asset.getTimestamp(),
            asset.getCreateDate()
          );

          logger.info("AssetCurrency updating... AssetCurrency:\n{}", assetCurrency);

          repository.save(assetCurrency);
        } else {
          assetCurrency = repository.save(asset);

          logger.info("New Asset saved! AssetCurrency:\n{}", assetCurrency);
        }

        return ResponseEntity.status(HttpStatus.OK).body(assetCurrency.toDTO());
      }
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }
}
