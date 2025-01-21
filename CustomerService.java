package com.rewards.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewards.model.Customer;
import com.rewards.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

  
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    
    
}
