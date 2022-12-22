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

  public AssetCurrencyDTO toDTO() {
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

  public AssetCurrency copy(
    String code,
    String codeIn,
    String name,
    Double high,
    Double low,
    Double saleValue,
    Float percentageChange,
    Long timestamp,
    String createDate
  ) {
    return new AssetCurrency(
      this.id,
      code != null ? code : this.code,
      codeIn != null ? codeIn : this.codeIn,
      name != null ? name : this.name,
      high != null ? high : this.high,
      low != null ? low : this.low,
      saleValue != null ? saleValue : this.saleValue,
      percentageChange != null ? percentageChange : this.percentageChange,
      timestamp != null ? timestamp : this.timestamp,
      createDate != null ? createDate : this.createDate
    );
  }

  public String getCodeName() {
    return this.getName().substring(0, this.getName().indexOf('/'));
  }

  public void setName(String name) {
    this.name = name;
  }
}