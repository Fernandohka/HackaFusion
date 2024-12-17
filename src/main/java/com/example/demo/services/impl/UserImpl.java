package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.example.demo.dto.AnswerDto;
import com.example.demo.dto.ForumDto;
import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.TopicDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.VoteDto;
import com.example.demo.dto.Web.AnwserProfileDto;
import com.example.demo.dto.Web.QuestProfileDto;
import com.example.demo.model.Topic;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.ImageStorageService;
import com.example.demo.services.UserService;
import com.example.demo.util.ImageUtils;

public class UserImpl implements UserService {

    @Autowired
    UserRepo repo;

    @Autowired
    BcryptImpl crypt;

    @Autowired
    ImageStorageService imageServ;

    @Override
    public User create(String nome, String edv, String email, String password, String numero) {
        try {
            var newUser = new User();
            newUser.setName(nome);
            newUser.setEdv(edv);
            newUser.setEmail(email);
            newUser.setPassword(crypt.encode(password));
            newUser.setNumber(numero);
            newUser.setAdmin(false);
            newUser.setEts(false);
            newUser.setImage(imageServ.UploadImage(ImageUtils.compressImage(Files.readAllBytes(Paths.get("C:\\Users\\disrct\\Desktop\\HackaFusion\\src\\main\\java\\com\\example\\demo\\images\\user.png"))))); 
            newUser.setDescription("");
            return repo.save(newUser);
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    @Override
    public ListPageDto<UserDto> getAll(Integer page, Integer size, String query) {
        List<User> listUser;
        if(query == null || query.equals(""))
            listUser = repo.findAll();
        else
            listUser = repo.findByNameContainsOrEmailContainsOrEdvContains(query, query, query);

        Integer start = 0;
        Integer end = listUser.size();
        Integer pages = size>0?(int)Math.ceilDiv(listUser.size(), size):0;

        var newList = new ArrayList<UserDto>();

        if (size > 0 && page > 0) {
            start = (page - 1) * size;
            if (start >= listUser.size())
                return new ListPageDto<>(pages, newList);
            end = start + size < listUser.size() ? start + size : listUser.size();
        }

        for (int i = start; i < end; i++) {
            var user = listUser.get(i);
            newList.add(new UserDto(user.getId(),user.getName(), user.getEdv(), user.getEmail(),user.getNumber(),imageServ.toUrl(user.getImage()), user.getEts()));
        }

        return new ListPageDto<>(pages, newList);
    }

    @Override
    public UserDto getById(Long idUser) {
        var opUser = repo.findById(idUser);
        if (!opUser.isPresent())
            return null;
        var user = opUser.get();
        return new UserDto(user.getId(),user.getName(), user.getEdv(), user.getEmail(), user.getNumber(), imageServ.toUrl(user.getImage()), user.getEts());
    }

    @Override
    public ResponseDto delete(Long idUser) {
        var opUser = repo.findById(idUser);
        if (!opUser.isPresent())
            return new ResponseDto(false, "Usuario não encontrado!!");
        var user = opUser.get();
        repo.delete(user);
        return new ResponseDto(true, "Usuario deletado com sucesso!!");
    }

    @Override
    public ResponseDto update(Long idUser, String nome, String edv, String email, String password, String numero,
            Boolean admin, Boolean ets, MultipartFile image, String description) {
        try {
            var opUser = repo.findById(idUser);
            if (!opUser.isPresent())
                return new ResponseDto(false, "Usuario não encontrado!!");

        var user = opUser.get();

            user.setName(nome!=null?nome:user.getName());
            user.setEdv(edv!=null?edv:user.getEdv());
            user.setEmail(email!=null?email:user.getEmail());
            user.setPassword(password!=null?crypt.encode(password):user.getPassword());
            user.setNumber(numero!=null?numero:user.getNumber());
            user.setAdmin(false);
            user.setEts(false);
            user.setDescription(description!=null?description:user.getDescription());
            if(image!=null){
                var res = imageServ.UpdateImage(user.getImage(), image);
                if (!res.success()) {
                    return res;
                }
            }
            
            repo.save(user);

            return new ResponseDto(true, "Usuario modificado usuario!!");

        } catch (Exception e) {
            return new ResponseDto(false, "Erro ao modificar usuario!!");
        }

    }

    @Override
    public ResponseDto updatePass(Long id, String password, String newPassword) {

        var userOp = repo.findById(id);

        if(!userOp.isPresent())
            return new ResponseDto(false,"Usuario não encontrado");
        var user = userOp.get();

        if(!crypt.checkEncode(password,user.getPassword())){
            return new ResponseDto(false,"Senha Incorreta");
        }

        user.setPassword(crypt.encode(newPassword));

        repo.save(user);

        return new ResponseDto(true, "senha atualizado com sucesso!");
    }

    @Override
    public ListPageDto<QuestProfileDto> interactionQuest(Long id, Integer page, Integer size) {
        var userOp = repo.findById(id);

        if(!userOp.isPresent())
            return null;
        var user = userOp.get();

        var listQuestions = new ArrayList<>(user.getQuestions());
        var newList = new ArrayList<QuestProfileDto>();

        Integer start = 0;
        Integer end = listQuestions.size();
        Integer pages = size>0?(int)Math.ceilDiv(listQuestions.size(), size):0;


        if (size > 0 && page > 0) {
            start = (page - 1) * size;
            if (start >= listQuestions.size())
                return new ListPageDto<>(pages, newList);
            end = start + size < listQuestions.size() ? start + size : listQuestions.size();
        }

        var currUser = new UserDto(user.getId(),user.getName(), user.getEdv(), user.getEmail(),user.getNumber(),imageServ.toUrl(user.getImage()), user.getEts());

        for (int i = start; i < end; i++) {
            var question = listQuestions.get(i);
            var forum = question.getForum();
            newList.add(new QuestProfileDto(currUser,question.getId(),question.getTitle(),question.getTitle(),new ForumDto(forum.getId(), forum.getName(), forum.getDescription())));
        }


        return new ListPageDto<>(pages, newList);
    }

    @Override
    public ListPageDto<AnwserProfileDto> interactionAnwser(Long id, Integer page, Integer size) {
        var userOp = repo.findById(id);

        if(!userOp.isPresent())
            return null;
        var user = userOp.get();

        var listAnswers = new ArrayList<>(user.getAnswers());
        var newList = new ArrayList<AnwserProfileDto>();

        Integer start = 0;
        Integer end = listAnswers.size();
        Integer pages = size>0?(int)Math.ceilDiv(listAnswers.size(), size):0;


        if (size > 0 && page > 0) {
            start = (page - 1) * size;
            if (start >= listAnswers.size())
                return new ListPageDto<>(pages, newList);
            end = start + size < listAnswers.size() ? start + size : listAnswers.size();
        }

        var currUser = new UserDto(user.getId(),user.getName(), user.getEdv(), user.getEmail(),user.getNumber(),imageServ.toUrl(user.getImage()), user.getEts());

        for (int i = start; i < end; i++) {
            var answer = listAnswers.get(i);
            var question = answer.getQuestion();
            var userQuestion = question.getUser();
            var forum = question.getForum();
            var answerVotes = new ArrayList<>(answer.getVotes());
            var votes = new VoteDto[answerVotes.size()];

            int j=0;
            for (var currVote : answerVotes) {
                var thisUser = currVote.getUser();
                votes[j] = new VoteDto(
                    currVote.getId(),
                    currVote.isUp(),
                    new UserDto(thisUser.getId(), thisUser.getName(), thisUser.getEdv(), thisUser.getEmail(), thisUser.getNumber(), imageServ.toUrl(thisUser.getImage()), thisUser.getEts())
                );
            }


            newList.add(new AnwserProfileDto(
                    new UserDto(userQuestion.getId(), userQuestion.getName(), userQuestion.getEdv(), userQuestion.getEmail(), userQuestion.getNumber(), imageServ.toUrl(userQuestion.getImage()), userQuestion.getEts()), 
                    question.getId(), 
                    question.getTitle(), 
                    question.getDescription(), 
                    new ForumDto(forum.getId(), forum.getName(), forum.getDescription()),
                    new AnswerDto(answer.getId(), answer.getDescription(), currUser, 
                    votes)
                )
            );
        }


        return new ListPageDto<>(pages, newList);
    }

    @Override
    public ListPageDto<TopicDto> interactionTopic(Long id, Integer page, Integer size) {
        var userOp = repo.findById(id);

        if(!userOp.isPresent())
            return null;
        var user = userOp.get();

        var userMessageTopics = new ArrayList<>(user.getMessagesTopic());
        var topicsUser = new ArrayList<Topic>();

        for (var messageTopic : userMessageTopics) {
            if(!topicsUser.contains(messageTopic.getTopic()))
                topicsUser.add(messageTopic.getTopic());
        }

        var newList = new ArrayList<TopicDto>();

        Integer start = 0;
        Integer end = topicsUser.size();
        Integer pages = size>0?(int)Math.ceilDiv(topicsUser.size(), size):0;

        if (size > 0 && page > 0) {
            start = (page - 1) * size;
            if (start >= topicsUser.size())
                return new ListPageDto<>(pages, newList);
            end = start + size < topicsUser.size() ? start + size : topicsUser.size();
        }

        for (int i = start; i < end; i++) {
            var topic = topicsUser.get(i);
            newList.add(new TopicDto(topic.getId(), topic.getName(), topic.getDescription()));
        }

        return new ListPageDto<>(pages, newList);
    }

    @Override
    public ResponseDto updateAdmin(Long id) {
        var userOp = repo.findById(id);

        if(!userOp.isPresent())
            return new ResponseDto(false,"Usuario não encontrado");
        var user = userOp.get();

        user.setAdmin(true);

        repo.save(user);

        return new ResponseDto(true, "Usuario atualizado para administrador com sucesso!");
    }
}
