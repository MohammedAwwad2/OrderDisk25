package com.example.orderdesk.web.dto;

import com.example.orderdesk.domain.OrderStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record CreateOrderRequest(
        @NotNull Long customerId,
        @NotNull @Min(0) BigDecimal amount,
        @NotNull @Pattern(regexp = "^[A-Z]{3}$") String currency,
        @NotNull OrderStatus status
) {}
