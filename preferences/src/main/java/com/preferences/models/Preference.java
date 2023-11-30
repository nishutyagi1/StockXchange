package com.preferences.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Preference {

  public String stockId;
  @Indexed(unique = true)
  public String prefId;
  public String preference;
  public String stockName;
  public String priceChange;
  public String changePercent;
  public String userId;

}
