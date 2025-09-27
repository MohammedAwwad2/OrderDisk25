package com.example.orderdesk.web.dto;

import com.example.orderdesk.domain.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderResponse(
        Long id,
        Long customerId,
        BigDecimal amount,
        String currency,
        OrderStatus status,
        Instant createdAt
) {}
