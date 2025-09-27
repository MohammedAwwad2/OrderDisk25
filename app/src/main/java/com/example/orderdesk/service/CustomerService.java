package com.example.orderdesk.service;

import com.example.orderdesk.domain.Customer;
import com.example.orderdesk.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public Customer create(Customer c) {
        return repo.save(c);
    }

    @Transactional(readOnly = true)
    public List<Customer> list() {
        return repo.findAll();
    }
}
