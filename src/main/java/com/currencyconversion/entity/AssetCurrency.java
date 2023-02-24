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

import java.text.NumberFormat;
import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    return new AssetCurrencyDTO(
      this.code.toUpperCase().concat("/").concat(this.name.split("/")[0]),
      this.codeIn.toUpperCase().concat("/").concat(this.name.split("/")[1]),
      calculate(this.high, this.codeIn.toUpperCase()),
      calculate(this.saleValue, this.codeIn.toUpperCase()),
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

  private static String calculate(double value, String type) {
    String formatedValue = NumberFormat
      .getCurrencyInstance(new Locale(type))
      .format(value);

    Pattern pattern = Pattern.compile("\\d.*");
    Matcher matcher = pattern.matcher(formatedValue);

    boolean isFinded = matcher.find();

    if (isFinded) {
      return matcher
        .group()
        .replace(",", "@")
        .replace(".", ",")
        .replace("@", ".");
    } else {
      return "ERRO";
    }
  }
}