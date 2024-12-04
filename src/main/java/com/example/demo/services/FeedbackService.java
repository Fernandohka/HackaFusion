package com.example.demo.services;

public interface FeedbackService {
    public Feedback post(Long idUserSender, Long idUserReceiver, String description, Boolean isPrivate);
    public ResponseDto get(Long idUser, Integer page, Integer size);
}
