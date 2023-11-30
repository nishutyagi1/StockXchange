package com.stock.repository;

import com.stock.models.Preference;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PreferenceRepository extends MongoRepository<Preference, String> {

  List<Preference> findByPrefIdAndUserId(String prefId,String userId);
}
