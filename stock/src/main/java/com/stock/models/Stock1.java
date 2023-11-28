package com.stock.models;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data

public class Stock1 implements Serializable {

  public String name;
  public BigDecimal price;
  public BigDecimal change;
  public double change_percent;

  public BigDecimal previous_close;

  public String country_code;

  public String exchange;

  public String currency;

}
