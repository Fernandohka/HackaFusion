package com.example.demo.dto.Web;

import java.time.LocalDateTime;

public record CreateProjectMessageDto(
    Long idProject, 
    String description, 
    LocalDateTime timestamp
) {
    
}
