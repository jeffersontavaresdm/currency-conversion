package com.currencyconversion.entity.enuns;

public enum AssetType {
  BRL("Real Brasileiro"),
  USD("Dólar Americano"),
  CAD("Dólar Canadense"),
  GBP("Libra Esterlina"),
  ARS("Peso Argentino"),
  BTC("Bitcoin"),
  LTC("Litecoin"),
  EUR("Euro"),
  JPY("Iene Japonês"),
  CHF("Franco Suíço"),
  AUD("Dólar Australiano"),
  CNY("Yuan Chinês"),
  ILS("Novo Shekel Israelense"),
  ETH("Ethereum"),
  XRP("XRP"),
  DOGE("Dogecoin");

  private final String name;

  AssetType(String type) {
    name = type;
  }

  public String value() {
    return name;
  }
}