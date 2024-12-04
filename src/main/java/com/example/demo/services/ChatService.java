package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Chat;

public interface ChatService {
    public Chat create(Long idUserA, Long idUserB);
    public List<Chat> getAllByUser(Long iduser);
}
