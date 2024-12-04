package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.ForumDto;
import com.example.demo.dto.ResponseDto;

public interface ForumService {
    public ForumDto create(String name, String description);
    public List<ForumDto> getAll(Integer page, Integer size);
    public ForumDto getById(Long idForum);
    public ResponseDto delete(Long idForum);
}
