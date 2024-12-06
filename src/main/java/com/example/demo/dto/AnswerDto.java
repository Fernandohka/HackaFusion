package com.example.demo.dto;

public record AnswerDto(
    Long id,
    String description,
    UserDto user,
    VoteDto[] votes
) {
    
}
