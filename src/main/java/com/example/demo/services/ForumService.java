package com.example.demo.services;

import java.util.List;

public interface ForumService {
    public Forum post(String name, String description);
    public List<Forum> getAll(Integer page, Integer size);
    public  getById(Long idForum);
    public ResponseDto delete(Long idForum);
}
