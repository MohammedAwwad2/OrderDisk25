package com.example.orderdesk.web.dto;

import java.math.BigDecimal;

public record DailyStatsResponse(Long orderCount, BigDecimal totalAmount) {}
