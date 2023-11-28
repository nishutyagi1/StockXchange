package com.stock.models;

import lombok.Data;

@Data
public class Preference {

  public String prefId;
  public String preference;
  public String stockId;
  public String stockName;
  public String priceChange;
  public String changePercent;
  public String userId;

}
