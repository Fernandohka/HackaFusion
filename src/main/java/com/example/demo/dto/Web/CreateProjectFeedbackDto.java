package com.example.demo.dto.Web;

public record CreateProjectFeedbackDto(
    Long idProject, 
    String description, 
    Boolean isPrivate
) {
    
}
