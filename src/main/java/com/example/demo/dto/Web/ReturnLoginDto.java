package com.example.demo.dto.Web;

public record ReturnLoginDto(
    String token,
    String message,
    boolean admin
) {}
