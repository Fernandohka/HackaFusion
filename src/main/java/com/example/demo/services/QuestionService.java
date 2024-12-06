package com.example.demo.services;


import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.ResponseDto;

public interface QuestionService {
    public QuestionDto create(Long idUser, Long idForum, String title, String description);
    public ListPageDto<QuestionDto> getAll(Integer page, Integer size);
    public QuestionDto getById(Long idQuestion, Integer answerPage, Integer answerSize);
    public ResponseDto delete(Long idQuestion);
}
