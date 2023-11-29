package com.preferences.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.preferences.models.Preference;
import com.preferences.repository.PreferenceRepository;
import java.util.Objects;
import java.util.Optional;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreferenceServiceImpl implements PreferenceService {

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  PreferenceRepository preferenceRepository;

  @Override
  public void save(ConsumerRecord<String, String> record) throws JsonProcessingException {
    Preference preference = objectMapper.readValue(record.value(), Preference.class);
    String prefId = preference.getPrefId();
    if(!Objects.nonNull(preferenceRepository.findById(prefId))) {
      preferenceRepository.save(preference);
    }
    else {
      System.out.println("Record exists");
    }
  }
}
