package com.example.demo.services;

public interface ProjectFeedbackService {
    public Feedback postFeedback(long idProject, long idUser, String description, Boolean isPrivate);
    public ResponseDto getFeedbackByUser(long idProject, long idUser);
    public ResponseDto getAllFeedback(long idProject);
}
