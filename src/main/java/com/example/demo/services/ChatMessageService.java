package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.dto.ResponseDto;
import com.example.demo.model.MessageChat;

public interface ChatMessageService {
    public MessageChat createMessage(Long idUser, Long idChat, LocalDateTime timestamp, String description);
    public List<MessageChat> getMessage(Long idChat);
    public ResponseDto deleteMessage(Long idMessage);
}
