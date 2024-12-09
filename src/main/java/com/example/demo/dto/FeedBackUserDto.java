package com.example.demo.dto;

public record FeedBackUserDto(
    Long id,
    UserDto userReceiver,
    UserDto userSender,
    String description,
    Boolean isPrivate
) {
    
}
