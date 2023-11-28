package com.stock.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.models.Preference;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Slf4j
@Component
public class StockProducer {

  @Autowired
  KafkaTemplate<String, String> kafkaTemplate;
  @Autowired
  private ObjectMapper mapper;

  @Value("${kafka.topic.name}")
  private String topic;

  public CompletableFuture<SendResult<String, String>> producePrefsEvent(Preference prefEvent)
      throws JsonProcessingException {
    log.info("StockProducer.producePrefsEvent() start");
    String key = prefEvent.getPrefId();
    String value = mapper.writeValueAsString(prefEvent);
    ProducerRecord<String, String> producerRecord = getProducerRecord(topic, key, value);
    CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(producerRecord);
    future.whenComplete((result, ex) -> {
      if (ex == null) {
        handleSuccess(result);
      } else {
        handleFailureError(ex);
      }
    });

    log.info("StockProducer.producePrefsEvent() end");
    return future;
  }

  private ProducerRecord<String, String> getProducerRecord(String topicName, String key,
      String value) {
    List<Header> headers = List.of(new RecordHeader("event-source", "scanner".getBytes()));
    return new ProducerRecord<String, String>(topicName, null, key, value, headers);
  }

  public static void handleSuccess(SendResult<String, String> sendResult) {
    log.info("Message produced on partition {}", sendResult.getRecordMetadata().partition());
    Stream.iterate(0, n -> n + 1)
        .limit(3)
        .forEach(n -> {
          log.info("counting i {}", n);
          sleep(1000);
        });
  }

  public static void handleFailureError(Throwable ex) {
    log.error("error occurred while producing record {}", ex.getMessage());
  }

  public static void sleep(long ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


}
