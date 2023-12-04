package com.stock.service;

import com.stock.entity.Preference;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface PrefService {

  public void saveOrUpdatePrefs(ConsumerRecord<String, String> record);

  public void save(Preference event);

  public void update(Preference event);
}