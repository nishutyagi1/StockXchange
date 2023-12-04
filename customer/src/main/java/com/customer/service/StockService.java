package com.customer.service;

import com.customer.models.Stock;
import java.util.List;

public interface StockService {
  List<Stock> getStocksByCustomerPref();
}
