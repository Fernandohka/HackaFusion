package com.example.demo.services;

public interface AnswerService {
    public Answer post(Long idUser, Long isQuestion, String description);
    public ResponseDto delete(Long idAnswer);
    public Vote addVote(Boolean up, Long idUser, Long isAnswer);
}
