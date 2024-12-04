package com.example.demo.services;

import java.time.LocalDateTime;

public interface ProjectMessageService {
    public Message postMessage(long idProject, long idUser, String message, LocalDateTime timestamp);
    public ResponseDto getMessage(long idProject);
}