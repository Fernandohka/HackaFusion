package com.example.demo.services;

import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Answer;
import com.example.demo.model.Vote;

public interface AnswerService {
    public Answer create(Long idUser, Long isQuestion, String description);
    public ResponseDto delete(Long idAnswer);
    public Vote addVote(Boolean up, Long idUser, Long isAnswer);
}
