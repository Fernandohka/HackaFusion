package com.example.demo.dto.Web;

public record CreateFeedbackUserDto(
    Long idUserReceiver,
    String description,
    Boolean isPrivate
) {
    
}
