package com.example.demo.dto;

public record NotificationDto(
    Long id,
    String description,
    UserDto user
) {
    
}
