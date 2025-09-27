package com.example.orderdesk.service;

import com.example.orderdesk.domain.Customer;
import com.example.orderdesk.domain.OrderEntity;
import com.example.orderdesk.repository.CustomerRepository;
import com.example.orderdesk.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orders;
    private final CustomerRepository customers;

    public OrderService(OrderRepository orders, CustomerRepository customers) {
        this.orders = orders;
        this.customers = customers;
    }

    @Transactional
    public OrderEntity create(Long customerId, OrderEntity order) {
        Customer c = customers.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        order.setCustomer(c);
        return orders.save(order);
    }

    @Transactional(readOnly = true)
    public List<OrderEntity> listByCustomer(Long customerId) {
        return orders.findByCustomer_Id(customerId);
    }
}
