package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectMessageService {
    public Message postMessage(Long idProject, Long idUser, String message, LocalDateTime timestamp);
    public List<ProjectMessage> getAllMessage(Long idProject, Integer page, Integer size);
}