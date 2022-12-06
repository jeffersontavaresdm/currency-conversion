package com.currencyconversion.entity;

import com.currencyconversion.entity.dto.AssetCurrencyDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.OffsetDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AssetCurrency {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonProperty("code")
  private String code;
  @JsonProperty("codein")
  private String codeIn;
  @JsonProperty("name")
  private String name;
  @JsonProperty("high")
  private Double high;
  @JsonProperty("low")
  private Double low;
  @JsonProperty("ask")
  private Double saleValue;
  @JsonProperty("pctChange")
  private Float percentageChange;

  @JsonProperty("timestamp")
  private Long timestamp;
  @JsonProperty("create_date")
  private String createDate;

  public AssetCurrencyDTO assetCurrencyHandler() {
    BigDecimal sum = new BigDecimal(this.low).add(new BigDecimal(this.high));
    BigDecimal divider = new BigDecimal("2");
    BigDecimal average = sum.divide(divider, new MathContext(5));

    return new AssetCurrencyDTO(
      this.code.toUpperCase().concat("/").concat(this.name.split("/")[0]),
      this.codeIn.toUpperCase().concat("/").concat(this.name.split("/")[1]),
      new BigDecimal(average.toString()).setScale(2, RoundingMode.HALF_DOWN).toString(),
      new BigDecimal(this.saleValue).setScale(2, RoundingMode.HALF_UP).toString(),
      OffsetDateTime.parse(this.createDate.replace(" ", "T").concat("Z"))
    );
  }

  public AssetCurrency copy(AssetCurrency asset) {
    return new AssetCurrency(
      this.id,
      asset.code,
      asset.codeIn,
      asset.name,
      asset.high,
      asset.low,
      asset.saleValue,
      asset.percentageChange,
      asset.timestamp,
      asset.createDate
    );
  }

  public String getCodeName() {
    return this.getName().substring(0, this.getName().indexOf('/'));
  }
}