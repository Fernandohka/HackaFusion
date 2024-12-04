package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

public interface ChatMessageService {
    public Message postMessage(Long idUser, Long idChat, LocalDateTime timestamp, String description);
    public List<Message> getMessage(Long idChat);
    public ResponseDto deleteMessage(Long idMessage);
}
