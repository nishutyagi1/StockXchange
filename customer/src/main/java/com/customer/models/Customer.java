package com.customer.models;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@Document(collection = "Customer")
public class Customer implements Serializable {

  @Id
  public String userId;
  public String firstName;
  public String lastName;
  public String email;
  public List<Preference> preferences;
}
