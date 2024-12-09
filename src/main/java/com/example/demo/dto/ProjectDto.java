package com.example.demo.dto;

import java.time.LocalDateTime;

public record ProjectDto(
    Long id,
    String name, 
    String description, 
    Boolean status, 
    LocalDateTime startDate, 
    LocalDateTime endDate, 
    String category
) {
    
}
