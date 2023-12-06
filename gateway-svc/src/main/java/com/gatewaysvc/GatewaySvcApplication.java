package com.gatewaysvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class GatewaySvcApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewaySvcApplication.class, args);
  }

  @Bean
  KeyResolver keyResolver() {
		return exchange -> Mono.just("ANONYMOUS");
	}

}
