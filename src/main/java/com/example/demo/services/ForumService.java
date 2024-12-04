package com.example.demo.services;

import java.util.List;
import com.example.demo.dto.ResponseDto;

public interface ForumService {
    public Forum post(String name, String description);
    public List<Forum> getAll(Integer page, Integer size);
    public Forum getById(Long idForum);
    public ResponseDto delete(Long idForum);
}
