package com.example.orderdesk.web;

import com.example.orderdesk.domain.Customer;
import com.example.orderdesk.web.dto.CreateCustomerRequest;
import com.example.orderdesk.web.dto.CustomerResponse;
import com.example.orderdesk.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerResponse create(@RequestBody @Valid CreateCustomerRequest req) {
        Customer c = new Customer();
        c.setName(req.name());
        c.setEmail(req.email());
        c = service.create(c);
        return new CustomerResponse(c.getId(), c.getName(), c.getEmail());
    }

    @GetMapping
    public List<CustomerResponse> list() {
        return service.list().stream()
                .map(c -> new CustomerResponse(c.getId(), c.getName(), c.getEmail()))
                .toList();
    }
}
