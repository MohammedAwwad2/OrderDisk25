package com.example.orderdesk.web;

import com.example.orderdesk.domain.OrderEntity;
import com.example.orderdesk.service.OrderService;
import com.example.orderdesk.web.dto.CreateOrderRequest;
import com.example.orderdesk.web.dto.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@RequestBody @Valid CreateOrderRequest req) {
        OrderEntity o = new OrderEntity();
        o.setAmount(req.amount());
        o.setCurrency(req.currency());
        o.setStatus(req.status());
        var saved = service.create(req.customerId(), o);
        return new OrderResponse(saved.getId(), saved.getCustomer().getId(), saved.getAmount(),
                saved.getCurrency(), saved.getStatus(), saved.getCreatedAt());
    }

    @GetMapping
    public List<OrderResponse> listByCustomer(@RequestParam Long customerId) {
        return service.listByCustomer(customerId).stream()
                .map(o -> new OrderResponse(o.getId(), o.getCustomer().getId(), o.getAmount(),
                        o.getCurrency(), o.getStatus(), o.getCreatedAt()))
                .toList();
    }
}
