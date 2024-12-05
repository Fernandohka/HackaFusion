package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Notification;

public interface NotificationService {
    public List<Notification> getAll(Long idUser, Integer page, Integer size);
}
