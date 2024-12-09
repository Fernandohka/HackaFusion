package com.example.demo.services;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.TopicDto;

public interface TopicService {
    public TopicDto create(String name, String description);
    public ListPageDto<TopicDto> getAll(Integer page, Integer size);
    public TopicDto getById(Long idTopic);
    public ResponseDto delete(Long idTopic);
}
