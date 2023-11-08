package com.customer.repository;

import com.customer.models.Preference;
import java.util.List;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

public interface PreferenceRepository extends MongoRepository<Preference, String> {

  List<Preference> findByUserId(String userId);
}
