package com.currencyconversion.controller;

import com.currencyconversion.entity.dto.AssetCurrencyDTO;
import com.currencyconversion.entity.dto.AssetTypes;
import com.currencyconversion.service.CurrencyConverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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

  @GetMapping("/convert/{c1}-{c2}")
  public ResponseEntity<AssetCurrencyDTO> convert(@PathVariable String c1, @PathVariable String c2) throws IOException {
    return currencyConverterService.convert(c1, c2);
  }
}
