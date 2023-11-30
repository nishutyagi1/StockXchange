package com.stock.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class StockAdvice {

  @ExceptionHandler({CustomerPrefServiceException.class})
  public ResponseEntity<String> handleException(CustomerPrefServiceException ex) {
    String errorMsg = ex.getMessage();
    log.info("error messages are : {}", errorMsg);
    return new ResponseEntity<>(ex.getHttpStatus());
  }
}
