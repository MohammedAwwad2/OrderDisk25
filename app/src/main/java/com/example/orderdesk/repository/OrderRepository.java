package com.example.orderdesk.repository;

import com.example.orderdesk.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByCustomer_Id(Long customerId);

    @Query("""
            SELECT COALESCE(SUM(o.amount), 0), COUNT(o)
            FROM OrderEntity o
            WHERE o.createdAt >= :start AND o.createdAt < :end
            """)
    Object[] sumAndCountBetween(Instant start, Instant end);
}
