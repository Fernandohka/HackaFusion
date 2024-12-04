package com.example.demo.services;

public interface NotificationService {
    public ResponseDto getAll(Long idUser, Integer page, Integer size);
}
