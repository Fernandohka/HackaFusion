package com.example.demo.services;

import com.example.demo.dto.FeedBackProjectDto;
import com.example.demo.dto.ListPageDto;

public interface ProjectFeedbackService {
    public FeedBackProjectDto createFeedback(Long idProject, Long idUser, String description, Boolean isPrivate);
    public ListPageDto<FeedBackProjectDto> getFeedbackByUser(Long idProject, Long idUser, Integer page, Integer size);
    public ListPageDto<FeedBackProjectDto> getAllFeedback(Long idProject, Integer page, Integer size);
}
