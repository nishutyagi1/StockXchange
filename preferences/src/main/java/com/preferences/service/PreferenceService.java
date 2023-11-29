package com.preferences.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface PreferenceService {

  public void save(ConsumerRecord<String, String> record) throws JsonProcessingException;
}
