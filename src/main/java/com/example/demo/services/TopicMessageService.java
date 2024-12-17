package com.example.demo.services;

import java.time.LocalDateTime;

import com.example.demo.dto.MessageTopicDto;
import com.example.demo.dto.ResponseDto;

public interface TopicMessageService {
    public MessageTopicDto createMessage(Long idTopic, Long idUser, String description);
    public ResponseDto deleteMessage(Long idTopicMessage);
}
