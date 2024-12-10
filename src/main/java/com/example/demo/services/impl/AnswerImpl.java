package com.example.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.AnswerDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.VoteDto;
import com.example.demo.model.Answer;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.model.Vote;
import com.example.demo.repositories.AnswerRepository;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.AnswerService;
import com.example.demo.services.ImageStorageService;

public class AnswerImpl implements AnswerService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    QuestionRepository questionRepo;

    @Autowired
    AnswerRepository answerRepo;

    @Autowired
    ImageStorageService imageServ;

    @Override
    public AnswerDto create(Long idUser, Long idQuestion, String description) {
        if(idUser == null || idQuestion == null || description == null || description.equals(""))
            return null;

        User user;
        Question question;
        
        try {
            user = userRepo.findById(idUser).get();
            question = questionRepo.findById(idQuestion).get();
        } catch (Exception e) {
            return null;
        }
        
        Answer answer = new Answer();
        answer.setUser(user);
        answer.setQuestion(question);
        answer.setDescription(description);
        answerRepo.save(answer);

        return new AnswerDto(
            answer.getId(), 
            answer.getDescription(), 
            new UserDto(user.getId(), user.getName(), user.getEdv(), user.getEmail(), user.getNumber(), imageServ.toUrl(user.getImage())),
            null);
    }

    @Override
    public ResponseDto delete(Long idAnswer) {
        try {
            answerRepo.deleteById(idAnswer);
            return new ResponseDto(true, "Answer deletion success");
        } catch (Exception e) {
            return new ResponseDto(false, "Answer deletion failed");
        }
    }

    @Override
    public VoteDto addVote(Boolean up, Long idUser, Long idAnswer) {
        if(up == null || idUser == null || idAnswer == null){
            return null;
        }

        User user;
        Answer answer;

        try {
            user = userRepo.findById(idUser).get();
            answer = answerRepo.findById(idAnswer).get();
        } catch (Exception e) {
            return null;
        }

        Vote vote = new Vote();
        vote.setUp(up);
        vote.setAnswer(answer);
        vote.setUser(user);

        return new VoteDto(vote.getId(), up, new UserDto(user.getId(), user.getName(), user.getEdv(), user.getEmail(), user.getNumber(), imageServ.toUrl(user.getImage())));
    }
    
}