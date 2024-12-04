package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.ForumDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Forum;
import com.example.demo.repositories.ForumRepository;
import com.example.demo.services.ForumService;

public class ForumImpl implements ForumService {

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

        return new ForumDto(forum.getId(), forum.getName(), forum.getDescription());
    }

    @Override
    public List<ForumDto> getAll(Integer page, Integer size) {
        if(page == null || size == null || page < 1 || size < 1)
            return null;

        var listForum = forumRepo.findAll();
        var newList = new ArrayList<ForumDto>();

        Integer start = 0;
        Integer end = listForum.size();
        
        if(size > 0 || page > 0){
            start = (size-1)*page;
            if(start >= listForum.size())
                return newList;
            end = start+size<listForum.size()?start+size:listForum.size();
        }

        for(int i=start;i<end;i++)
            newList.add(new ForumDto(listForum.get(i).getId(), listForum.get(i).getName(), listForum.get(i).getDescription()));

        return newList;
    }

    @Override
    public ForumDto getById(Long idForum) {
        try {
            var forum = forumRepo.findById(idForum).get();
            return new ForumDto(forum.getId(), forum.getName(), forum.getDescription());
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
