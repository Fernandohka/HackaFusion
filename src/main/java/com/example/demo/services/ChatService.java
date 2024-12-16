package com.example.demo.services;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.Web.ChatDto;
import com.example.demo.dto.Web.MessageDtoPriv;
import com.example.demo.model.Chat;

public interface ChatService {
    public Chat create(Long idUserA, Long idUserB);
    public ListPageDto<ChatDto> getAllByUser(Long iduser, String query);
    public ResponseDto createMessage(Long idUser, Long idChat, String description);
    public ListPageDto<MessageDtoPriv> getMessage(Long isUser, Long idChat);
}
