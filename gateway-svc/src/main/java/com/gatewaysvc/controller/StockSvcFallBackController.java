package com.gatewaysvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
public class StockSvcFallBackController {

    @GetMapping("/stock-service-fallback")
    Flux<String> getStocks(){
        log.info("StockSvcFallBackController: getStocks : inside fallback");
        return  Flux.just("error occurred");
    }
}
