package com.example.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.FeedBackProjectDto;
import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.FeedBackProject;
import com.example.demo.model.Project;
import com.example.demo.model.User;
import com.example.demo.repositories.FeedBackProjectRepository;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.ImageStorageService;
import com.example.demo.services.ProjectFeedbackService;

public class ProjectFeedbackImpl implements ProjectFeedbackService {

    @Autowired
    FeedBackProjectRepository feedbackProjectRepo;

    @Autowired
    ProjectRepository projectRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ImageStorageService imageServ;

    @Override
    public FeedBackProjectDto createFeedback(Long idProject, Long idUser, String description, Boolean isPrivate) {
        if(idProject == null || description == null || description.equals("") || idUser == null || isPrivate == null)
            return null;

        Project project;
        User user;
        
        try {
            project = projectRepo.findById(idProject).get();
            user = userRepo.findById(idUser).get();
        } catch (Exception e) {
            return null;
        }
        
        FeedBackProject feedback = new FeedBackProject();
        feedback.setDescription(description);
        feedback.setPriv(isPrivate);
        feedback.setProject(project);
        feedback.setUser(user);
        feedbackProjectRepo.save(feedback);

        return new FeedBackProjectDto(
            feedback.getId(), 
            feedback.getDescription(),
            feedback.isPriv(),
            new UserDto(
                user.getId(), 
                user.getName(), 
                user.getEdv(), 
                user.getEmail(), 
                user.getNumber(), 
                imageServ.toUrl(user.getImage()),
                user.getEts(),
                user.isAdmin()
                )
            );
    }

    @Override
    public ListPageDto<FeedBackProjectDto> getFeedbackByUser(Long idProject, Long idUser, Integer page, Integer size) {
        if(page == null || size == null || page < 1 || size < 1 || idProject == null || idUser == null )
            return null;

        var listFeedback = new ArrayList<>(userRepo.findById(idUser).get().getFeedBackProjects());
        var newList = new ArrayList<FeedBackProjectDto>();

        Integer start = 0;
        Integer end = listFeedback.size();
        Integer pages = (int)Math.floor(listFeedback.size()/size);
        
        if(size > 0 && page > 0){
            start = (page-1)*size;
            if(start >= listFeedback.size())
                return new ListPageDto<>(pages, newList);
            end = start+size<listFeedback.size()?start+size:listFeedback.size();
        }

        FeedBackProject feedback;
        User user;
        for(int i=start;i<end;i++){
            feedback = listFeedback.get(i);
            user = feedback.getUser();
            newList.add(new FeedBackProjectDto(
                feedback.getId(),
                feedback.getDescription(),
                feedback.isPriv(),
                new UserDto(
                    user.getId(), 
                    user.getName(), 
                    user.getEdv(), 
                    user.getEmail(), 
                    user.getNumber(), 
                    imageServ.toUrl(user.getImage()),
                    user.getEts(),
                    user.isAdmin()
                )
            ));
        }
        return new ListPageDto<>(pages, newList);
    }

    @Override
    public ListPageDto<FeedBackProjectDto> getAllFeedback(Long idProject, Integer page, Integer size) {
        if(page == null || size == null || page < 1 || size < 1 || idProject == null)
            return null;

        var listFeedback = feedbackProjectRepo.findAll();
        var newList = new ArrayList<FeedBackProjectDto>();

        Integer start = 0;
        Integer end = listFeedback.size();
        Integer pages = (int)Math.floor(listFeedback.size()/size);
        
        if(size > 0 || page > 0){
            start = (page-1)*size;
            if(start >= listFeedback.size())
                return new ListPageDto<>(pages, newList);
            end = start+size<listFeedback.size()?start+size:listFeedback.size();
        }

        FeedBackProject feedback;
        User user;
        for(int i=start;i<end;i++){
            feedback = listFeedback.get(i);
            user = feedback.getUser();

            newList.add(new FeedBackProjectDto(
                feedback.getId(),
                feedback.getDescription(),
                feedback.isPriv(),
                new UserDto(
                    user.getId(), 
                    user.getName(), 
                    user.getEdv(), 
                    user.getEmail(), 
                    user.getNumber(), 
                    imageServ.toUrl(user.getImage()),
                    user.getEts(),
                    user.isAdmin()
                    )
                ));
        }
        return new ListPageDto<>(pages, newList);
    }
    
}
