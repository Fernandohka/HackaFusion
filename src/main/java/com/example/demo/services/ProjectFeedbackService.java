package com.example.demo.services;

import java.util.List;

public interface ProjectFeedbackService {
    public Feedback postFeedback(Long idProject, Long idUser, String description, Boolean isPrivate);
    public List<ProjectFeedback> getFeedbackByUser(Long idProject, Long idUser);
    public List<ProjectFeedback> getAllFeedback(Long idProject, Integer page, Integer size);
}
