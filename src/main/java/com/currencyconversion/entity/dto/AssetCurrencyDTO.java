package com.currencyconversion.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record AssetCurrencyDTO(
  @JsonProperty("from") String convertFrom,
  @JsonProperty("to") String convertedTo,
  @JsonProperty("convertedValue") String value,
  @JsonProperty("saleValue") String sale,
  @JsonProperty("lastUpdate") OffsetDateTime date
) {}