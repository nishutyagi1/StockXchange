package com.customer.repository;

import com.customer.models.Preference;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PreferenceRepository extends MongoRepository<Preference, String> {

  List<Preference> findByUserId(String userId);


}
