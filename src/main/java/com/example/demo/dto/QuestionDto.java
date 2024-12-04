package com.example.demo.dto;

public record QuestionDto(
    Long id,
    UserDto[] user,
    ForumDto[] forum,
    String title,
    String description
) {
    
}
