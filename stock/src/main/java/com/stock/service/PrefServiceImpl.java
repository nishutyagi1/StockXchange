package com.stock.service;

import com.stock.models.Preference;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

@Service
public class PrefServiceImpl implements PrefService {

  @Override
  public void saveOrUpdatePrefs(ConsumerRecord<String, String> record) {

  }

  @Override
  public void save(Preference event) {

  }

  @Override
  public void update(Preference event) {

  }
}
