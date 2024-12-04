package com.example.demo.services;

public interface ForumService {
    public Forum post(String name, String description);
    public ResponseDto getAll(Integer page, Integer size);
    public ResponseDto getById(Long idForum);
    public ResponseDto delete(Long idForum);
}
