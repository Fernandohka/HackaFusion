package com.example.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.ForumDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Forum;
import com.example.demo.repositories.ForumRepository;
import com.example.demo.services.ForumService;

public class ForumImplementation implements ForumService {

    @Autowired
    ForumRepository forumRepo;

    @Override
    public ForumDto create(String name, String description) {
        if(name == null || name.equals(""))
            return null;

        if(description == null || description.equals(""))
            return null;
        
        Forum forum = new Forum();
        forum.setName(name);
        forum.setDescription(description);
        forumRepo.save(forum);

        return new ForumDto(forum.getName(), forum.getDescription());
    }

    @Override
    public List<ForumDto> getAll(Integer page, Integer size) {
        return null;
    }

    @Override
    public ForumDto getById(Long idForum) {
        try {
            var forum = forumRepo.findById(idForum).get();
            return new ForumDto(forum.getName(), forum.getDescription());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ResponseDto delete(Long idForum) {
        try {
            forumRepo.deleteById(idForum);
            return new ResponseDto(true, "Forum deletion success");
        } catch (Exception e) {
            return new ResponseDto(false, "Forum deletion failed");
        }
    }
    
}
