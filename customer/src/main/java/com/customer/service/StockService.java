package com.customer.service;

import com.customer.models.Stock;
import com.customer.models.StockResponse;
import java.util.List;

public interface StockService {
  StockResponse getStocksByCustomerPref(String userId);
}
