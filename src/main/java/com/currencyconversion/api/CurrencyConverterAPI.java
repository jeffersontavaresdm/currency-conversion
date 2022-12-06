package com.currencyconversion.api;

import com.currencyconversion.entity.AssetCurrency;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.Map;

public interface CurrencyConverterAPI {

  @GET("/all")
  Call<Map<String, AssetCurrency>> all();

  @GET("/json/last/{c01}-{c02}")
  Call<Map<String, AssetCurrency>> convert(
    @Path("c01") String c01,
    @Path("c02") String c02
  );
}
