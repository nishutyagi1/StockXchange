package com.stock.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class StockAdvice {

  @ExceptionHandler({CustomerPrefServiceException.class})
  public ResponseEntity<ErrorResponse> handleException(CustomerPrefServiceException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getMessage());
    String errorMsg = ex.getMessage();
    log.info("error messages are : {}", errorMsg);
    return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
  }
}
