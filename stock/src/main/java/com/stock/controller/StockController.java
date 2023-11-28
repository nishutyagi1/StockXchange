package com.stock.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stock.models.Preference;
import com.stock.models.Stock;
import com.stock.models.StockResponse;
import com.stock.producer.StockProducer;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class StockController {

  @Autowired
  WebClient webClient;

  @Autowired
  private StockProducer stockProducer;
  @Value("${stock.service.baseUri}")
  private String stockSvcUrl;

  @Value("${stock.service.search}")
  private String searchPath;
  List<String> stockNames = List.of("AAPL", "Microsoft NASDAQ", "Dow Johns", "USD to EUR");

  @GetMapping("/stock")
  public List<Stock> getStocks() {
    String language = "en";
    var stocks = new ArrayList<Stock>();

    for (var stockName : stockNames) {
      StockResponse stockRes = webClient.method(HttpMethod.GET).uri(
              builder -> builder.path("/search").queryParam("language", language)
                  .queryParam("query", stockName).build())
          .headers(headers -> headers.addAll(getHeader())).retrieve()
          .bodyToMono(StockResponse.class).block();
      if (stockRes != null && stockRes.getData() != null) {
        stocks.addAll(stockRes.getData().getStock());
      }
    }
    return stocks;
  }

  @PostMapping("/addPreference")
  public ResponseEntity<String> addPreferences(@RequestBody Preference preference) {
    try {
      stockProducer.producePrefsEvent(preference);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (JsonProcessingException e) {
      log.info("Error occurred");
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public HttpHeaders getHeader() {
    var httpHeaders = new HttpHeaders();
    httpHeaders.set("X-RapidAPI-Key", "1f722d6952mshc49bd8b9870f2a6p165ca0jsnb6484eb8bef7");
    httpHeaders.set("X-RapidAPI-Host", "real-time-finance-data.p.rapidapi.com");
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return httpHeaders;
  }

}
