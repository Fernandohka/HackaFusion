package com.example.demo.services;

import java.time.LocalDateTime;

import com.example.demo.dto.ResponseDto;
import com.example.demo.model.MessageTopic;

public interface TopicMessageService {
    public MessageTopic postMessage(Long idTopic, Long idUser, String description, LocalDateTime timestamp);
    public ResponseDto deleteMessage(Long idTopicMessage);
}
