package com.preferences.repository;

import com.preferences.models.Preference;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PreferenceRepository extends MongoRepository<Preference, String> {


}
