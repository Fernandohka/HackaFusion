package com.example.demo.services;

import com.example.demo.dto.AnswerDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.VoteDto;

public interface AnswerService {
    public AnswerDto create(Long idUser, Long idQuestion, String description);
    public ResponseDto delete(Long idAnswer);
    public VoteDto addVote(Boolean up, Long idUser, Long idAnswer);
}
