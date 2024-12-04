package com.example.demo.services;

import java.time.LocalDateTime;

public interface TopicMessageService {
    public MessageTopic postMessage(Long idTopic, Long idUser, String description, LocalDateTime timestamp);
    public ResponseDto delete(Long idTopicMessage);
}
