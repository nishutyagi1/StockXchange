package com.customer.service;

import com.customer.models.Stock;
import com.customer.models.StockResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class StockServiceImpl implements StockService {

  @Autowired
  WebClient webClient;

  @Value("${stock.service.uri}")
  String stockServiceUri;
  @Override
  public List<Stock> getStocksByCustomerPref() {
    String language ="en";
   StockResponse response =
     webClient.method(HttpMethod.GET)
        .uri(stockServiceUri)
        .header("token","")
        .header("language","en")
        /*.header("stockName",stockName)*/
        .retrieve()
         .bodyToMono(StockResponse.class)
         .block();
    return response.getData().getStock();
  }
}
