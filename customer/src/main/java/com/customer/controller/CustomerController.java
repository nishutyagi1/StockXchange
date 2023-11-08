package com.customer.controller;

import com.customer.models.Preference;
import com.customer.repository.CustomerRepository;
import com.customer.repository.PreferenceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CustomerController {

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public PreferenceRepository preferenceRepository;

    @GetMapping("/userId")
    public List<Preference> fetchPreferencesById(@PathVariable(value = "userId") String userId){
        List<Preference> preferences = preferenceRepository.findByUserId(userId);
        return preferences;
    }
}
