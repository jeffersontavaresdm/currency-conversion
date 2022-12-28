package com.currencyconversion.entity.dto;

import java.util.List;

public record ResponseResultDTO(List<AssetCurrencyDTO> assets, Long entityCount) {}
