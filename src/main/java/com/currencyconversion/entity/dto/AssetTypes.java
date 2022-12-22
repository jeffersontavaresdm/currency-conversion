package com.currencyconversion.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AssetTypes(@JsonProperty("currencyTypes") List<String> types) {}