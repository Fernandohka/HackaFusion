package com.example.demo.services;

import java.util.List;

public interface FeedbackService {
    public Feedback post(Long idUserSender, Long idUserReceiver, String description, Boolean isPrivate);
    public List<Feedback> get(Long idUser, Integer page, Integer size);
}