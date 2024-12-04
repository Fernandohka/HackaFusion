package com.example.demo.services;

import java.util.List;

public interface FeedbackService {
    public FeedbackService create(Long idUserSender, Long idUserReceiver, String description, Boolean isPrivate);
    public List<FeedbackService> get(Long idUser, Integer page, Integer size);
}
