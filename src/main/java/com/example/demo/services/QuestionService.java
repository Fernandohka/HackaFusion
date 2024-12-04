package com.example.demo.services;

public interface QuestionService {
    public Question post(Long idUser, Long idForum, String title, String description);
    public ResponseDto getAll(Integer page, Integer size);
    public ResponseDto getById(Long idQuestion, Integer answerPage, Integer answerSize);
    public ResponseDto delete(Long idQuestion);
}
