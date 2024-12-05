package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.ForumDto;
import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Forum;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.repositories.ForumRepository;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.QuestionService;

public class QuestionImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepo;
    
    @Autowired
    UserRepo userRepo;

    @Autowired
    ForumRepository forumRepo;

    @Override
    public QuestionDto create(Long idUser, Long idForum, String title, String description) {
        if(idUser == null || idForum == null || title == null || title.equals("") || description == null || description.equals(""))
            return null;

        User user;
        Forum forum;
        
        try {
            user = userRepo.findById(idUser).get();
            forum = forumRepo.findById(idForum).get();
        } catch (Exception e) {
            return null;
        }
        
        Question question = new Question();
        question.setUser(user);
        question.setForum(forum);
        question.setTitle(title);
        question.setDescription(description);
        questionRepo.save(question);

        return new QuestionDto(question.getId(), new UserDto(user.getId(), user.getName(), user.getEdv(), user.getEmail(), user.getNumber(), user.getImage()), new ForumDto(forum.getId(), forum.getName(), forum.getDescription()), question.getTitle(), question.getDescription());
    }

    @Override
    public List<QuestionDto> getAll(Long idUser, Long idForum, Integer page, Integer size) {
        if(page == null || size == null || page < 1 || size < 1)
            return null;

        var listQuestion = questionRepo.findAll();
        var newList = new ArrayList<QuestionDto>();

        Integer start = 0;
        Integer end = listQuestion.size();
        
        if(size > 0 || page > 0){
            start = (size-1)*page;
            if(start >= listQuestion.size())
                return newList;
            end = start+size<listQuestion.size()?start+size:listQuestion.size();
        }

        User user;
        Forum forum;
        
        try {
            user = userRepo.findById(idUser).get();
            forum = forumRepo.findById(idForum).get();
        } catch (Exception e) {
            return null;
        }

        for(int i=start;i<end;i++)
            // newList.add(new QuestionDto(listQuestion.get(i).getId(), new UserDto(user.getId(), user.getName(), user.getEdv(), user.getEmail(), user.getNumber(), user.getImage()), new ForumDto(forum.getId(), forum.getName(), forum.getDescription()), listQuestion.get(i).getDescription()));
        
        return newList;
    }

    @Override
    public QuestionDto getById(Long idQuestion, Integer answerPage, Integer answerSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public ResponseDto delete(Long idQuestion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
