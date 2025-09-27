package com.example.orderdesk.web;

import com.example.orderdesk.service.StatsService;
import com.example.orderdesk.web.dto.DailyStatsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/stats")
public class StatsController {
    private final StatsService stats;

    public StatsController(StatsService stats) {
        this.stats = stats;
    }

    @GetMapping("/daily")
    public DailyStatsResponse daily() {
        var d = stats.daily();
        return new DailyStatsResponse(d.orderCount(), d.totalAmount());
    }
}
