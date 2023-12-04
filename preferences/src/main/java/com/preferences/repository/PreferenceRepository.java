package com.preferences.repository;

import com.preferences.entity.Preference;
import com.preferences.entity.PreferenceKey;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PreferenceRepository extends MongoRepository<Preference, PreferenceKey> {


}
