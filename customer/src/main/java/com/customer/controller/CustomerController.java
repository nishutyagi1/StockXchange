package com.customer.controller;

import com.customer.models.StockResponse;
import com.customer.repository.CustomerRepository;
import com.customer.repository.PreferenceRepository;
import com.customer.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CustomerController {

  @Autowired
  public CustomerRepository customerRepository;

  @Autowired
  public PreferenceRepository preferenceRepository;

  @Autowired
  public StockService stockService;

  @GetMapping("/{userId}")
  public StockResponse fetchPreferencesById(@PathVariable(value = "userId") String userId) {
  //  List<Preference> preferences = preferenceRepository.findByUserId(userId);
     return stockService.getStocksByCustomerPref(userId);
  }

}
