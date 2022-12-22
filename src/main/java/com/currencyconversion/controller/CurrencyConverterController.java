package com.currencyconversion.controller;

import com.currencyconversion.entity.dto.AssetCurrencyDTO;
import com.currencyconversion.entity.dto.AssetTypes;
import com.currencyconversion.entity.enuns.AssetType;
import com.currencyconversion.service.CurrencyConverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CurrencyConverterController {

  private final CurrencyConverterService currencyConverterService;

  public CurrencyConverterController(CurrencyConverterService currencyConverterService) {
    this.currencyConverterService = currencyConverterService;
  }

  @GetMapping("/types")
  public AssetTypes getTypes() {
    return currencyConverterService.types();
  }

  @GetMapping("/all")
  public ResponseEntity<List<AssetCurrencyDTO>> all() {
    return currencyConverterService.all();
  }

  @GetMapping("/convert")
  public ResponseEntity<AssetCurrencyDTO> convert(
    @RequestParam("from") String currency01,
    @RequestParam("to") String currency02
  ) {
    return currencyConverterService.convert(
      AssetType.valueOf(currency01),
      AssetType.valueOf(currency02)
    );
  }
}
