package com.example.demo.dto;

public record VoteDto(
    Long id,
    Boolean up,
    UserDto user
) {
    
}
