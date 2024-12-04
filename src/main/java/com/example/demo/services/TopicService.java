package com.example.demo.services;

import java.util.List;
import com.example.demo.dto.ResponseDto;

public interface TopicService {
    public Topic post(String name, String description);
    public List<Topic> getAll(Integer page, Integer size);
    public Topic getById(Long idTopic);
    public ResponseDto delete(Long idTopic);
}