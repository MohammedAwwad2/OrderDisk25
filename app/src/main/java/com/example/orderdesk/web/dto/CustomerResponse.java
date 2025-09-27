package com.example.orderdesk.web.dto;

public record CustomerResponse(
        Long id,
        String name,
        String email
) {}
