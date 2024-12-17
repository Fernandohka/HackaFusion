package com.example.demo.services;



import com.example.demo.dto.ForumDto;
import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;

public interface ForumService {
    public ForumDto create(String name, String description);
    public ListPageDto<ForumDto> getAll(Integer page, Integer size, String query);
    public ForumDto getById(Long idForum);
    public ResponseDto delete(Long idForum);
}
