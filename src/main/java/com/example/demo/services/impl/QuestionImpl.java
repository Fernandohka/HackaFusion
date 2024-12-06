package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

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
            new UserDto(user.getId(), user.getName(), user.getEdv(), user.getEmail(), user.getNumber(), imageServ.toUrl(user.getImage())), 
            new ForumDto(forum.getId(), forum.getName(), forum.getDescription()), 
            question.getTitle(), 
            question.getDescription(), 
            null);
    }

    @Override
    public ListPageDto<QuestionDto> getAll(Integer page, Integer size) {
        if(page == null || size == null || page < 1 || size < 1)
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

        for(int i=start;i<end;i++)
            newList.add(new QuestionDto(
                listQuestion.get(i).getId(), 
                new UserDto(listQuestion.get(i).getUser().getId(), listQuestion.get(i).getUser().getName(), listQuestion.get(i).getUser().getEdv(), listQuestion.get(i).getUser().getEmail(), listQuestion.get(i).getUser().getNumber(), imageServ.toUrl(listQuestion.get(i).getUser().getImage())), 
                new ForumDto(listQuestion.get(i).getForum().getId(), listQuestion.get(i).getForum().getName(), listQuestion.get(i).getForum().getDescription()), 
                listQuestion.get(i).getTitle(), 
                listQuestion.get(i).getDescription(),
                null));
        return new ListPageDto<>(pages, newList);
    }

    @Override
    public QuestionDto getById(Long idQuestion, Integer answerPage, Integer answerSize) {
        try {
            var question = questionRepo.findById(idQuestion).get();
            var answers = new AnswerDto[question.getAnswers().size()];
            var iterator = question.getAnswers().iterator();
            for(int i = 0; i < question.getAnswers().size(); i++){
                if(iterator.hasNext()){
                    Answer answer = iterator.next();

                    var votes = new VoteDto[answer.getVotes().size()];
                    var iteratorVotes = answer.getVotes().iterator();
                    for(int j = 0; j < answer.getVotes().size(); j++){
                        if(iteratorVotes.hasNext()){
                            Vote vote = iteratorVotes.next();
                            votes[j] = new VoteDto(vote.getId(), vote.isUp(), new UserDto(vote.getUser().getId(), vote.getUser().getName(), vote.getUser().getEdv(), vote.getUser().getEmail(), vote.getUser().getNumber(), imageServ.toUrl(vote.getUser().getImage())));
                        }
                    }

                    answers[i] = new AnswerDto(answer.getId(), answer.getDescription(), new UserDto(answer.getUser().getId(), answer.getUser().getName(), answer.getUser().getEdv(), answer.getUser().getEmail(), answer.getUser().getNumber(), imageServ.toUrl(answer.getUser().getImage())), votes);
                }
            }

            return new QuestionDto(
                question.getId(), 
                new UserDto(question.getUser().getId(), question.getUser().getName(), question.getUser().getEdv(), question.getUser().getEmail(), question.getUser().getNumber(), imageServ.toUrl(question.getUser().getImage())), 
                new ForumDto(question.getForum().getId(), question.getForum().getName(), question.getForum().getDescription()), 
                question.getTitle(), 
                question.getDescription(), 
                answers);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ResponseDto delete(Long idQuestion) {
        try {
            questionRepo.deleteById(idQuestion);
            return new ResponseDto(true, "Question deletion success");
        } catch (Exception e) {
            return new ResponseDto(false, "Question deletion failed");
        }
    }
}
