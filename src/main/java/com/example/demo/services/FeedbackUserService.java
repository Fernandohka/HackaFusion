package com.example.demo.services;

import com.example.demo.dto.FeedBackUserDto;
import com.example.demo.dto.ListPageDto;

public interface FeedbackUserService {
    public FeedBackUserDto create(Long idUserSender, Long idUserReceiver, String description, Boolean isPrivate);
    public ListPageDto<FeedBackUserDto> getAll(Long idUser, Integer page, Integer size);
}
