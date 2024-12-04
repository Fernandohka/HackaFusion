package com.example.demo.services;

import java.time.LocalDateTime;

public interface ProjectMessageService {
    public Message postMessage(Long idProject, Long idUser, String message, LocalDateTime timestamp);
    public ResponseDto getAllMessage(Long idProject);
}