package com.example.demo.services;


import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.TopicDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.Web.AnwserProfileDto;
import com.example.demo.dto.Web.QuestProfileDto;
import com.example.demo.model.User;

public interface UserService {
    public User create(String nome, String edv, String email, String password, String numero);
    public ListPageDto<UserDto> getAll(Integer page, Integer size, String query);
    public UserDto getById(Long idUser);
    public ResponseDto delete(Long idUser);
    public ResponseDto update(Long idUser,String nome, String edv, String email, String password, String numero, Boolean admin, Boolean ets, MultipartFile image, String description);
    public ResponseDto updatePass(Long id,String password,String newPassword);
    public ListPageDto<QuestProfileDto> interactionQuest(Long id,Integer page, Integer size);
    public ListPageDto<AnwserProfileDto> interactionAnwser(Long id,Integer page, Integer size);
    public ListPageDto<TopicDto>  interactionTopic(Long id,Integer page, Integer size);
}