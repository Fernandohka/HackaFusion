package com.example.demo.dto.Web;

import java.time.LocalDateTime;

public record CreateTopicMessageDto(
    Long idTopic,
    String description
) {
    
}
