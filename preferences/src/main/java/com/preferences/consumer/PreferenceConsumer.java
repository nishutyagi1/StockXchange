package com.preferences.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.preferences.service.PreferenceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PreferenceConsumer {

  @Autowired
  PreferenceService preferenceService;

  @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
  public void onMessage(ConsumerRecord<String, String> consumerRecord)
      throws JsonMappingException, JsonProcessingException {
    System.out.println("Inside PreferenceConsumer onMessage");
    preferenceService.save(consumerRecord);
    log.info("saved record is {} ", consumerRecord);
  }
}

