package com.stock.repository;

import com.stock.entity.Preference;
import com.stock.entity.PreferenceKey;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PreferenceRepository extends MongoRepository<Preference, PreferenceKey> {

  List<Preference> findByPreferenceKey(PreferenceKey key);

  List<Preference> findByUserId(String userId);
}
