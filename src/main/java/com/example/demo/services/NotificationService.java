package com.example.demo.services;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.NotificationDto;

public interface NotificationService {
    public ListPageDto<NotificationDto> getAll(Long idUser, Integer page, Integer size);
}
