package com.example.demo.services;

import java.util.List;

public interface TopicService {
    public Topic post(String name, String description);
    public List<Topic> getAll(Integer page, Integer size);
    public Topic getById(Long id, Integer page, Integer size);
}
