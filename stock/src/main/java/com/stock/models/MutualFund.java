package com.stock.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"symbol", "name", "type", "price", "change", "change_percent", "previous_close",
    "last_update_utc", "currency", "google_mid"})

public class MutualFund {

  @JsonProperty("symbol")
  private String symbol;
  @JsonProperty("name")
  private String name;
  @JsonProperty("type")
  private String type;
  @JsonProperty("price")
  private Double price;
  @JsonProperty("change")
  private Double change;
  @JsonProperty("change_percent")
  private Double changePercent;
  @JsonProperty("previous_close")
  private Double previousClose;
  @JsonProperty("last_update_utc")
  private String lastUpdateUtc;
  @JsonProperty("currency")
  private String currency;
  @JsonProperty("google_mid")
  private String googleMid;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

  @JsonProperty("symbol")
  public String getSymbol() {
    return symbol;
  }

  @JsonProperty("symbol")
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("type")
  public String getType() {
    return type;
  }

  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }

  @JsonProperty("price")
  public Double getPrice() {
    return price;
  }

  @JsonProperty("price")
  public void setPrice(Double price) {
    this.price = price;
  }

  @JsonProperty("change")
  public Double getChange() {
    return change;
  }

  @JsonProperty("change")
  public void setChange(Double change) {
    this.change = change;
  }

  @JsonProperty("change_percent")
  public Double getChangePercent() {
    return changePercent;
  }

  @JsonProperty("change_percent")
  public void setChangePercent(Double changePercent) {
    this.changePercent = changePercent;
  }

  @JsonProperty("previous_close")
  public Double getPreviousClose() {
    return previousClose;
  }

  @JsonProperty("previous_close")
  public void setPreviousClose(Double previousClose) {
    this.previousClose = previousClose;
  }

  @JsonProperty("last_update_utc")
  public String getLastUpdateUtc() {
    return lastUpdateUtc;
  }

  @JsonProperty("last_update_utc")
  public void setLastUpdateUtc(String lastUpdateUtc) {
    this.lastUpdateUtc = lastUpdateUtc;
  }

  @JsonProperty("currency")
  public String getCurrency() {
    return currency;
  }

  @JsonProperty("currency")
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @JsonProperty("google_mid")
  public String getGoogleMid() {
    return googleMid;
  }

  @JsonProperty("google_mid")
  public void setGoogleMid(String googleMid) {
    this.googleMid = googleMid;
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
