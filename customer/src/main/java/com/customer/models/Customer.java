package com.customer.models;

import java.util.List;
import lombok.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@Document(collection = "Customer")
public class Customer implements Serializable{
    @Id
    public String userId;
    public String firstName;
    public String lastName;
    public String email;
    @DocumentReference(lookup="{'Customer':?#{#self._id} }")
    public List<Preference> preferences;
}
