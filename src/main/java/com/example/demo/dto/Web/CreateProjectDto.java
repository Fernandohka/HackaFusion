package com.example.demo.dto.Web;

import java.time.LocalDateTime;

public record CreateProjectDto(
    String name, 
    String description,
    LocalDateTime startDate, 
    LocalDateTime endDate, 
    Long idCategory
) {
    
}
