package com.currencyconversion.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record AssetTypes(@JsonProperty("currency_types") Map<String, String> types) {}