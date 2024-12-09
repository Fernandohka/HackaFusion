package com.example.demo.dto;

public record FeedBackProjectDto(
    Long id,
    String description,
    Boolean isPrivate,
    UserDto user
) {
    
}
