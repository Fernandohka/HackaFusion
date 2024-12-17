package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.FeedBackUserDto;
import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.FeedBackUser;
import com.example.demo.model.User;
import com.example.demo.repositories.FeedBackUserRepository;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.FeedbackUserService;
import com.example.demo.services.ImageStorageService;

public class UserFeedBackImpl implements FeedbackUserService {

    @Autowired
    FeedBackUserRepository feedbackRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ImageStorageService imageServ;

    @Override
    public FeedBackUserDto create(Long idUserSender, Long idUserReceiver, String description, Boolean isPrivate) {
        if(idUserSender == null || idUserReceiver == null || description == null || description.equals("") || isPrivate == null)
            return null;
        
        User receiver;
        User sender;
        
        try {
            receiver = userRepo.findById(idUserReceiver).get();
            sender = userRepo.findById(idUserSender).get();
        } catch (Exception e) {
            return null;
        }

        FeedBackUser feedback = new FeedBackUser();
        feedback.setUserSender(sender);
        feedback.setUserReceiver(receiver);
        feedback.setDescription(description);
        feedback.setPriv(isPrivate);
        feedbackRepo.save(feedback);

        return new FeedBackUserDto(
            feedback.getId(), 
            new UserDto(
                receiver.getId(), 
                receiver.getName(), 
                receiver.getEdv(), 
                receiver.getEmail(), 
                receiver.getNumber(), 
                imageServ.toUrl(receiver.getImage()),
                receiver.getEts(),
                receiver.isAdmin()
                ), 
            new UserDto(
                sender.getId(), 
                sender.getName(), 
                sender.getEdv(), 
                sender.getEmail(), 
                sender.getNumber(), 
                imageServ.toUrl(sender.getImage()),
                sender.getEts(),
                sender.isAdmin()
                ), 
            feedback.getDescription(),
            feedback.isPriv()
            );
    }

    @Override
    public ListPageDto<FeedBackUserDto> getAll(Long idUser, Integer page, Integer size) {
        if(page == null || size == null || idUser == null)
            return null;

        List<FeedBackUser> listFeedback = feedbackRepo.findByUserReceiverId(idUser);
        var newList = new ArrayList<FeedBackUserDto>();

        Integer start = 0;
        Integer end = listFeedback.size();
        Integer pages = size>0?(int)Math.ceilDiv(listFeedback.size(), size):0;
        
        if(size > 0 && page > 0){
            start = (page-1)*size;
            if(start >= listFeedback.size())
                return new ListPageDto<>(pages, newList);
            end = start+size<listFeedback.size()?start+size:listFeedback.size();
        }

        FeedBackUser feedback;
        User receiver;
        User sender;
        for(int i=start;i<end;i++){
            feedback = listFeedback.get(i);
            receiver = feedback.getUserReceiver();
            sender = feedback.getUserSender();

            newList.add(new FeedBackUserDto(
                feedback.getId(),
                new UserDto(
                    receiver.getId(), 
                    receiver.getName(), 
                    receiver.getEdv(), 
                    receiver.getEmail(), 
                    receiver.getNumber(), 
                    imageServ.toUrl(receiver.getImage()),
                    receiver.getEts(),
                    receiver.isAdmin()
                    ), 
                new UserDto(
                    sender.getId(), 
                    sender.getName(), 
                    sender.getEdv(), 
                    sender.getEmail(), 
                    sender.getNumber(), 
                    imageServ.toUrl(sender.getImage()),
                    sender.getEts(), 
                    sender.isAdmin()
                    ), 
                feedback.getDescription(),
                feedback.isPriv()
                ));
        }
        return new ListPageDto<>(pages, newList);
    }
}
