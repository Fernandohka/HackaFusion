package com.example.demo.dto;

import java.time.LocalDateTime;

public record MessageTopicDto(
    Long id,
    TopicDto idTopic,
    UserDto idUser,
    String description,
    LocalDateTime timestamp
) {
    
}
