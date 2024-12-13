package com.example.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.AnswerDto;
import com.example.demo.dto.ForumDto;
import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.VoteDto;
import com.example.demo.model.Answer;
import com.example.demo.model.Forum;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.model.Vote;
import com.example.demo.repositories.ForumRepository;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.ImageStorageService;
import com.example.demo.services.QuestionService;

public class QuestionImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepo;
    
    @Autowired
    UserRepo userRepo;

    @Autowired
    ForumRepository forumRepo;

    @Autowired
    ImageStorageService imageServ;

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

        return new QuestionDto(
            question.getId(), 
            new UserDto(
                user.getId(), 
                user.getName(), 
                user.getEdv(), 
                user.getEmail(), 
                user.getNumber(), 
                imageServ.toUrl(user.getImage()),
                user.getEts()
                ), 
            new ForumDto(
                forum.getId(), 
                forum.getName(), 
                forum.getDescription()
                ), 
            question.getTitle(), 
            question.getDescription(), 
            null);
    }

    @Override
    public ListPageDto<QuestionDto> getAll(Integer page, Integer size) {
        if(page == null || size == null)
            return null;

        var listQuestion = questionRepo.findAll();
        var newList = new ArrayList<QuestionDto>();

        Integer start = 0;
        Integer end = listQuestion.size();
        Integer pages = (int)Math.floor(listQuestion.size()/size);
        
        if(size > 0 || page > 0){
            start = (size-1)*page;
            if(start >= listQuestion.size())
                return new ListPageDto<>(pages, newList);
            end = start+size<listQuestion.size()?start+size:listQuestion.size();
        }

        Question question;
        User user;
        Forum forum;
        for(int i=start;i<end;i++){
            question = listQuestion.get(i);
            user = question.getUser();
            forum = question.getForum();
            newList.add(new QuestionDto(
                question.getId(), 
                new UserDto(
                    user.getId(), 
                    user.getName(), 
                    user.getEdv(), 
                    user.getEmail(), 
                    user.getNumber(), 
                    imageServ.toUrl(user.getImage()),
                    user.getEts()
                    ), 
                new ForumDto(
                    forum.getId(), 
                    forum.getName(), 
                    forum.getDescription()
                    ), 
                question.getTitle(), 
                question.getDescription(),
                null));
        }
        return new ListPageDto<>(pages, newList);
    }

    @Override
    public QuestionDto getById(Long idQuestion, Integer answerPage, Integer answerSize) {
        try {
            var question = questionRepo.findById(idQuestion).get();
            var answers = new AnswerDto[question.getAnswers().size()];
            var iterator = question.getAnswers().iterator();
            User user;
            Forum forum;
            for(int i = 0; i < question.getAnswers().size(); i++){
                if(iterator.hasNext()){
                    Answer answer = iterator.next();

                    var votes = new VoteDto[answer.getVotes().size()];
                    var iteratorVotes = answer.getVotes().iterator();
                    for(int j = 0; j < answer.getVotes().size(); j++){
                        if(iteratorVotes.hasNext()){
                            Vote vote = iteratorVotes.next();
                            user = vote.getUser();
                            votes[j] = new VoteDto(
                                        vote.getId(), 
                                        vote.isUp(), 
                                        new UserDto(
                                            user.getId(), 
                                            user.getName(), 
                                            user.getEdv(), 
                                            user.getEmail(), 
                                            user.getNumber(), 
                                            imageServ.toUrl(user.getImage()),
                                            user.getEts()
                                            )
                                        );
                        }
                    }

                    user = answer.getUser();
                    answers[i] = new AnswerDto(
                                    answer.getId(), 
                                    answer.getDescription(), 
                                    new UserDto(
                                        user.getId(), 
                                        user.getName(), 
                                        user.getEdv(), 
                                        user.getEmail(), 
                                        user.getNumber(), 
                                        imageServ.toUrl(user.getImage()),
                                        user.getEts()
                                        ), 
                                    votes
                                    );
                }
            }

            user = question.getUser();
            forum = question.getForum();
            return new QuestionDto(
                question.getId(), 
                new UserDto(
                    user.getId(), 
                    user.getName(), 
                    user.getEdv(), 
                    user.getEmail(), 
                    user.getNumber(), 
                    imageServ.toUrl(user.getImage()),
                    user.getEts()
                    ), 
                new ForumDto(
                    forum.getId(), 
                    forum.getName(), 
                    forum.getDescription()
                    ), 
                question.getTitle(), 
                question.getDescription(), 
                answers);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ResponseDto delete(Long idUser, Long idQuestion) {
        User user;
        Question question;

        try {
            user = userRepo.findById(idUser).get();
            question = questionRepo.findById(idQuestion).get();
        } catch (Exception e) {
            return new ResponseDto(false, "Erro ao deletar questão");
        }

        if(!question.getUser().equals(user) && !user.isAdmin())
            return new ResponseDto(false, "Usuario sem permissão de deletar questão");

        try {
            questionRepo.deleteById(idQuestion);
            return new ResponseDto(true, "Questão deletada com sucesso");
        } catch (Exception e) {
            return new ResponseDto(false, "Erro ao deletar questão");
        }
    }
}
