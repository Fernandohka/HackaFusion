package com.example.demo.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.MessageProjectDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.MessageProject;
import com.example.demo.model.Project;
import com.example.demo.model.User;
import com.example.demo.repositories.MessageProjectRepository;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.ImageStorageService;
import com.example.demo.services.ProjectMessageService;

public class ProjectMessageImpl implements ProjectMessageService {

    @Autowired
    ProjectRepository projectRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    MessageProjectRepository messageProjectRepo;

    @Autowired
    ImageStorageService imageServ;

    @Override
    public MessageProjectDto createMessage(Long idProject, Long idUser, String description, LocalDateTime timestamp) {
        if(idProject == null || idUser == null || description.equals("") || timestamp == null)
            return null;

        User user;
        Project project;

        try {
            user = userRepo.findById(idUser).get();
            project = projectRepo.findById(idUser).get();
        } catch (Exception e) {
            return null;
        }

        if(!project.getUsers().contains(user))
            return null;
        
        if(!project.isStatus())
            return null;

        MessageProject messageProject = new MessageProject();
        messageProject.setDescription(description);
        messageProject.setProject(project);
        messageProject.setTimestamp(timestamp);
        messageProject.setUser(user);
        messageProjectRepo.save(messageProject);

        return new MessageProjectDto(
            messageProject.getId(), 
            messageProject.getDescription(), 
            messageProject.getTimestamp(), 
            new UserDto(
                user.getId(), 
                user.getName(), 
                user.getEdv(), 
                user.getEmail(), 
                user.getNumber(), 
                imageServ.toUrl(user.getImage())
                )
            );
    }

    @Override
    public ListPageDto<MessageProjectDto> getAllMessage(Long idProject, Long idUser, Integer page, Integer size) {
        if(page == null || size == null || page < 1 || size < 1 || idProject == null)
            return null;

        try {
            Project project = projectRepo.findById(idProject).get();
            User user = userRepo.findById(idUser).get();

            if(!project.getUsers().contains(user))
                return null;
        } catch (Exception e) {
            return null;
        }
        
        var listMessage = messageProjectRepo.findByProjectId(idProject);
        var newList = new ArrayList<MessageProjectDto>();

        Integer start = 0;
        Integer end = listMessage.size();
        Integer pages = (int)Math.floor(listMessage.size()/size);
        
        if(size > 0 && page > 0){
            start = (page-1)*size;
            if(start >= listMessage.size())
                return new ListPageDto<>(pages, newList);
            end = start+size<listMessage.size()?start+size:listMessage.size();
        }

        for(int i=start;i<end;i++){
            var message = listMessage.get(i);
            var user = message.getUser();
            newList.add(new MessageProjectDto(
                message.getId(),
                message.getDescription(), 
                message.getTimestamp(),
                new UserDto(
                    user.getId(), 
                    user.getName(), 
                    user.getEdv(), 
                    user.getEmail(), 
                    user.getNumber(), 
                    imageServ.toUrl(user.getImage())
                    )
                ));
        }
        return new ListPageDto<>(pages, newList);
    }
    
}
