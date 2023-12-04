package com.preferences.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.preferences.entity.CustomerPreference;
import com.preferences.entity.Preference;
import com.preferences.entity.PreferenceKey;
import com.preferences.repository.PreferenceRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PreferenceServiceImpl implements PreferenceService {

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  PreferenceRepository preferenceRepository;

  @Override
  public void save(ConsumerRecord<String, String> record) throws JsonProcessingException {
    log.info("Inside save method");
    CustomerPreference  customerPreference = objectMapper.readValue(record.value(), CustomerPreference.class);
    PreferenceKey preferenceKey = PreferenceKey.builder()
        .prefId(customerPreference.getPrefId())
        .userId(customerPreference.getUserId())
        .build();
    Preference pref = Preference.builder()
        .preferenceKey(preferenceKey)
        .stockName(customerPreference.getStockName())
        .userId(customerPreference.getUserId())
        .build();
    preferenceRepository.save(pref);
    log.info("record saved successfully");
  }
}
