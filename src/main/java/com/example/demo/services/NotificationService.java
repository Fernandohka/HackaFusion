package com.example.demo.services;

import java.util.List;

public interface NotificationService {
    public List<Notification> getAll(Long idUser, Integer page, Integer size);
}
