package com.preferences.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPreference {
  public String userId;
  public String prefId;
  public String stockId;
  public String preference;
  public String stockName;
  public String priceChange;
  public String changePercent;

}
