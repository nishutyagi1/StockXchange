package com.customer.service;

import com.customer.models.Stock;
import com.customer.models.StockResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class StockServiceImpl implements StockService {

  @Autowired
  WebClient webClient;

  @Value("${stock.service.uri}")
  String stockServiceUri;


  @Override
  public StockResponse getStocksByCustomerPref(String userId) {
    String language = "en";
    StockResponse response =
        webClient.method(HttpMethod.GET)
            .uri(stockServiceUri + userId + "/stocks")
            .retrieve()
            .bodyToMono(StockResponse.class)
            .block();
    return response;
  }
}
