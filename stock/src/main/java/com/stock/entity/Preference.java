package com.stock.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Preference {

  @Id
  private PreferenceKey preferenceKey;
  public String userId;
  public String preference;
  public String stockName;
  public String priceChange;
  public String changePercent;

}
