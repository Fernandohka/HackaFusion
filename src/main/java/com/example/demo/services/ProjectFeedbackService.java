package com.example.demo.services;

public interface ProjectFeedbackService {
    public Feedback postFeedback(Long idProject, Long idUser, String description, Boolean isPrivate);
    public ResponseDto getFeedbackByUser(Long idProject, Long idUser);
    public ResponseDto getAllFeedback(Long idProject, Integer page, Integer size);
}
