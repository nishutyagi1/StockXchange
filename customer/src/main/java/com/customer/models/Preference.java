package com.customer.models;

import lombok.Data;

@Data
public class Preference {

  public String userId;
  public String prefId;
  public String stockId;
  public String preference;
  public String stockName;
  public String priceChange;
  public String changePercent;
}
