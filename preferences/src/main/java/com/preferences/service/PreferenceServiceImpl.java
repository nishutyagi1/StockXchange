package com.preferences.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.preferences.models.Preference;
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
    Preference preference = objectMapper.readValue(record.value(), Preference.class);
    String prefId = preference.getPrefId();
    preferenceRepository.save(preference);
    log.info("record saved successfully");
  }
}
