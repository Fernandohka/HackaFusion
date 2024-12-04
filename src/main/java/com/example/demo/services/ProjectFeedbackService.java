package com.example.demo.services;

import java.util.List;

import com.example.demo.model.FeedBackProject;

public interface ProjectFeedbackService {
    public FeedBackProject postFeedback(Long idProject, Long idUser, String description, Boolean isPrivate);
    public List<FeedBackProject> getFeedbackByUser(Long idProject, Long idUser);
    public List<FeedBackProject> getAllFeedback(Long idProject, Integer page, Integer size);
}
