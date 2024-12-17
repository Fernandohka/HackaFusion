package com.example.demo.services;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.MessageProjectDto;

public interface ProjectMessageService {
    public MessageProjectDto createMessage(Long idProject, Long idUser, String description);
    public ListPageDto<MessageProjectDto> getAllMessage(Long idProject, Long idUser, Integer page, Integer size);
}