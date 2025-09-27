package com.example.orderdesk.service;

import com.example.orderdesk.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Service
public class StatsService {
    private final OrderRepository orders;

    public StatsService(OrderRepository orders) {
        this.orders = orders;
    }

    @Transactional(readOnly = true)
    public DailyStats daily() {
        var start = LocalDate.now(ZoneOffset.UTC).atStartOfDay().toInstant(ZoneOffset.UTC);
        var end = start.plusSeconds(24 * 3600);
        Object[] agg = orders.sumAndCountBetween(start, end);
        BigDecimal totalAmount = (BigDecimal) agg[0];
        Long count = (Long) agg[1];
        return new DailyStats(count, totalAmount);
    }

    public record DailyStats(Long orderCount, BigDecimal totalAmount) {}
}
