package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.model.MessageProject;

public interface ProjectMessageService {
    public MessageProject createMessage(Long idProject, Long idUser, String message, LocalDateTime timestamp);
    public List<MessageProject> getAllMessage(Long idProject, Integer page, Integer size);
}