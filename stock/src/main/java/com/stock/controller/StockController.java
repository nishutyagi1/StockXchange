package com.stock.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.exception.CustomerPrefServiceException;
import com.stock.models.Preference;
import com.stock.models.Stock;
import com.stock.models.StockResponse;
import com.stock.producer.StockProducer;
import com.stock.repository.PreferenceRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.bson.json.JsonObject;
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
@RequestMapping("/api/v1")
@Slf4j
public class StockController {

  @Autowired
  PreferenceRepository preferenceRepository;
  @Autowired
  WebClient webClient;
  @Autowired
  private StockProducer stockProducer;
  @Value("${stock.service.baseUri}")
  private String stockSvcUrl;
  @Value("${stock.service.search}")
  private String searchPath;

  @Autowired
  ObjectMapper objectMapper;
  List<String> stockNames = List.of("AAPL"/*, "Microsoft NASDAQ", "Dow Johns", "USD to EUR"*/);

  @GetMapping("/stocks")
  public List<Stock> getStocks() {
    return fetchStocksFromThirdParty();
  }

  public List<Stock> fetchStocksFromThirdParty() {
    String language = "en";
    var stocks = new ArrayList<Stock>();

    for (var stockName : stockNames) {
      StockResponse stockRes = null;
      try {
        stockRes = objectMapper.readValue("{\n"
            + "    \"status\": \"OK\",\n"
            + "    \"request_id\": \"e1dac94a-b5a5-44b3-b1b7-e1ef976447d5\",\n"
            + "    \"data\": {\n"
            + "        \"stock\": [\n"
            + "            {\n"
            + "                \"symbol\": \"AAPL:NASDAQ\",\n"
            + "                \"name\": \"Apple Inc\",\n"
            + "                \"type\": \"stock\",\n"
            + "                \"price\": 189.37,\n"
            + "                \"change\": -1.03,\n"
            + "                \"change_percent\": -0.541,\n"
            + "                \"previous_close\": 190.4,\n"
            + "                \"last_update_utc\": \"2023-11-29 23:38:40\",\n"
            + "                \"country_code\": \"US\",\n"
            + "                \"exchange\": \"NASDAQ\",\n"
            + "                \"exchange_open\": \"2023-11-29 09:30:00\",\n"
            + "                \"exchange_close\": \"2023-11-29 16:00:00\",\n"
            + "                \"timezone\": \"America/New_York\",\n"
            + "                \"utc_offset_sec\": -18000,\n"
            + "                \"currency\": \"USD\",\n"
            + "                \"google_mid\": \"/m/07zmbvf\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"symbol\": \"APC:ETR\",\n"
            + "                \"name\": \"Apple Inc\",\n"
            + "                \"type\": \"stock\",\n"
            + "                \"price\": 173.34,\n"
            + "                \"change\": -0.38,\n"
            + "                \"change_percent\": -0.2187,\n"
            + "                \"previous_close\": 173.72,\n"
            + "                \"last_update_utc\": \"2023-11-29 19:31:30\",\n"
            + "                \"country_code\": \"DE\",\n"
            + "                \"exchange\": \"ETR\",\n"
            + "                \"exchange_open\": \"2023-11-29 09:00:00\",\n"
            + "                \"exchange_close\": \"2023-11-29 17:30:00\",\n"
            + "                \"timezone\": \"Europe/Berlin\",\n"
            + "                \"utc_offset_sec\": 3600,\n"
            + "                \"currency\": \"EUR\",\n"
            + "                \"google_mid\": \"/g/11f102cqtm\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"symbol\": \"MSFT:NASDAQ\",\n"
            + "                \"name\": \"Microsoft Corp\",\n"
            + "                \"type\": \"stock\",\n"
            + "                \"price\": 378.85,\n"
            + "                \"change\": -3.85,\n"
            + "                \"change_percent\": -1.006,\n"
            + "                \"previous_close\": 382.7,\n"
            + "                \"last_update_utc\": \"2023-11-29 23:38:30\",\n"
            + "                \"country_code\": \"US\",\n"
            + "                \"exchange\": \"NASDAQ\",\n"
            + "                \"exchange_open\": \"2023-11-29 09:30:00\",\n"
            + "                \"exchange_close\": \"2023-11-29 16:00:00\",\n"
            + "                \"timezone\": \"America/New_York\",\n"
            + "                \"utc_offset_sec\": -18000,\n"
            + "                \"currency\": \"USD\",\n"
            + "                \"google_mid\": \"/m/07zln_9\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"symbol\": \"AAPL:BMV\",\n"
            + "                \"name\": \"Apple Inc\",\n"
            + "                \"type\": \"stock\",\n"
            + "                \"price\": 3265.12,\n"
            + "                \"change\": 9.4102,\n"
            + "                \"change_percent\": 0.289,\n"
            + "                \"previous_close\": 3255.71,\n"
            + "                \"last_update_utc\": \"2023-11-29 21:23:22\",\n"
            + "                \"country_code\": \"MX\",\n"
            + "                \"exchange\": \"BMV\",\n"
            + "                \"exchange_open\": \"2023-11-29 08:30:00\",\n"
            + "                \"exchange_close\": \"2023-11-29 15:00:00\",\n"
            + "                \"timezone\": \"America/Mexico_City\",\n"
            + "                \"utc_offset_sec\": -21600,\n"
            + "                \"currency\": \"MXN\",\n"
            + "                \"google_mid\": \"/g/11bbrs9b72\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"symbol\": \"APC:FRA\",\n"
            + "                \"name\": \"Apple Inc\",\n"
            + "                \"type\": \"stock\",\n"
            + "                \"price\": 172.26,\n"
            + "                \"change\": -0.84,\n"
            + "                \"change_percent\": -0.4853,\n"
            + "                \"previous_close\": 173.1,\n"
            + "                \"last_update_utc\": \"2023-11-29 21:59:34\",\n"
            + "                \"country_code\": \"DE\",\n"
            + "                \"exchange\": \"FRA\",\n"
            + "                \"exchange_open\": \"2023-11-29 08:00:00\",\n"
            + "                \"exchange_close\": \"2023-11-29 22:00:00\",\n"
            + "                \"timezone\": \"Europe/Berlin\",\n"
            + "                \"utc_offset_sec\": 3600,\n"
            + "                \"currency\": \"EUR\",\n"
            + "                \"google_mid\": \"/m/02xl7ls\"\n"
            + "            }\n"
            + "        ],\n"
            + "        \"ETF\": [],\n"
            + "        \"index\": [],\n"
            + "        \"mutual_fund\": [\n"
            + "            {\n"
            + "                \"symbol\": \"APPIX:MUTF\",\n"
            + "                \"name\": \"Appleseed Fund Institutional Share\",\n"
            + "                \"type\": \"mutual_fund\",\n"
            + "                \"price\": 13.24,\n"
            + "                \"change\": 0.04,\n"
            + "                \"change_percent\": 0.303,\n"
            + "                \"previous_close\": 13.24,\n"
            + "                \"last_update_utc\": \"2023-11-28 00:00:00\",\n"
            + "                \"currency\": \"USD\",\n"
            + "                \"google_mid\": \"/g/1ywbr0sf1\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"symbol\": \"APPLX:MUTF\",\n"
            + "                \"name\": \"Appleseed Fund Investor Share\",\n"
            + "                \"type\": \"mutual_fund\",\n"
            + "                \"price\": 13.13,\n"
            + "                \"change\": 0.03,\n"
            + "                \"change_percent\": 0.229,\n"
            + "                \"previous_close\": 13.13,\n"
            + "                \"last_update_utc\": \"2023-11-28 00:00:00\",\n"
            + "                \"currency\": \"USD\",\n"
            + "                \"google_mid\": \"/g/1ywbqxm3g\"\n"
            + "            }\n"
            + "        ],\n"
            + "        \"currency\": [],\n"
            + "        \"futures\": []\n"
            + "    }\n"
            + "}", StockResponse.class);
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
      /*webClient.method(HttpMethod.GET).uri(
              builder -> builder.path("/search").queryParam("language", language)
                  .queryParam("query", stockName).build())
          .headers(headers -> headers.addAll(getHeader())).retrieve()
          .bodyToMono(StockResponse.class).block();*/

      if (stockRes != null && stockRes.getData() != null) {
        stocks.addAll(stockRes.getData().getStock());
      }
    }
    return stocks;
  }

  @PostMapping("/addPreference")
  public ResponseEntity<String> addPreferences(@RequestBody Preference preference) {
    try {

      List<Stock> stocks = fetchStocksFromThirdParty();
      Stock stock = stocks.parallelStream()
          .filter(stk -> stk.getSymbol().equalsIgnoreCase(preference.getPrefId())).findAny()
          .orElse(null);
      if (stock == null) {

        //Need to write own exception and Error handler (code, message) controller
      }

      Preference pref = preferenceRepository.findByPrefIdAndUserId(preference.getPrefId(),
          preference.getUserId()).stream().findAny().orElse(null);
      if (pref != null) {
        throw new CustomerPrefServiceException();
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
      stockProducer.producePrefsEvent(preference);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (JsonProcessingException e) {
      log.info("Error occurred");
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public HttpHeaders getHeader() {
    var httpHeaders = new HttpHeaders();
    httpHeaders.set("X-RapidAPI-Key", "5ed45ea069mshf95175d3386f0dbp1b13d5jsn8eb3ce042f71");
    httpHeaders.set("X-RapidAPI-Host", "real-time-finance-data.p.rapidapi.com");
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return httpHeaders;
  }

}
