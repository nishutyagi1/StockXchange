package com.customer.repository;

import com.customer.models.Customer;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

  //Optional<Customer> findByUserId(String s);
}
