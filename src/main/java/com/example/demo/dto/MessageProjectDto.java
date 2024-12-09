package com.example.demo.dto;

import java.time.LocalDateTime;

public record MessageProjectDto(
    Long id,
    String description,
    LocalDateTime timestamp,
    UserDto user
) {
    
}
