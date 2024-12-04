package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Topic;

public interface TopicService {
    public Topic create(String name, String description);
    public List<Topic> getAll(Integer page, Integer size);
    public Topic getById(Long idTopic);
    public ResponseDto delete(Long idTopic);
}
