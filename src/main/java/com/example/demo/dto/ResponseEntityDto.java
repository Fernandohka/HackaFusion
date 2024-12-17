package com.example.demo.dto;

public record ResponseEntityDto<T>(
    T entity,
    String message
) {}
