
package com.stock.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "stock",
    "ETF",
    "index",
    "mutual_fund",
    "currency",
    "futures"
})

public class Data {

  @JsonProperty("stock")
  private List<Stock> stock;
  @JsonProperty("ETF")
  private List<Object> etf;
  @JsonProperty("index")
  private List<Object> index;
  @JsonProperty("mutual_fund")
  private List<MutualFund> mutualFund;
  @JsonProperty("currency")
  private List<Object> currency;
  @JsonProperty("futures")
  private List<Object> futures;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

  @JsonProperty("stock")
  public List<Stock> getStock() {
    return stock;
  }

  @JsonProperty("stock")
  public void setStock(List<Stock> stock) {
    this.stock = stock;
  }

  @JsonProperty("ETF")
  public List<Object> getEtf() {
    return etf;
  }

  @JsonProperty("ETF")
  public void setEtf(List<Object> etf) {
    this.etf = etf;
  }

  @JsonProperty("index")
  public List<Object> getIndex() {
    return index;
  }

  @JsonProperty("index")
  public void setIndex(List<Object> index) {
    this.index = index;
  }

  @JsonProperty("mutual_fund")
  public List<MutualFund> getMutualFund() {
    return mutualFund;
  }

  @JsonProperty("mutual_fund")
  public void setMutualFund(List<MutualFund> mutualFund) {
    this.mutualFund = mutualFund;
  }

  @JsonProperty("currency")
  public List<Object> getCurrency() {
    return currency;
  }

  @JsonProperty("currency")
  public void setCurrency(List<Object> currency) {
    this.currency = currency;
  }

  @JsonProperty("futures")
  public List<Object> getFutures() {
    return futures;
  }

  @JsonProperty("futures")
  public void setFutures(List<Object> futures) {
    this.futures = futures;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}
