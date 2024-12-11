package com.example.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.NotificationDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Notification;
import com.example.demo.model.User;
import com.example.demo.repositories.NotificationRepository;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.ImageStorageService;
import com.example.demo.services.NotificationService;

public class NotificationImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ImageStorageService imageServ;

    @Override
    public ListPageDto<NotificationDto> getAll(Long idUser, Integer page, Integer size) {
        if(page == null || size == null)
            return null;
        
        User user;
        
        try {
            user = userRepo.findById(idUser).get();
        } catch (Exception e) {
            return null;
        }

        var listNotification = notificationRepo.findAll();
        var newList = new ArrayList<NotificationDto>();

        Integer start = 0;
        Integer end = listNotification.size();
        Integer pages = (int)Math.floor(listNotification.size()/size);
        
        if(size > 0 && page > 0){
            start = (page-1)*size;
            if(start >= listNotification.size())
                return new ListPageDto<>(pages, newList);
            end = start+size<listNotification.size()?start+size:listNotification.size();
        }

        Notification notification;
        for(int i=start;i<end;i++){
            notification = listNotification.get(i);

            newList.add(new NotificationDto(
                notification.getId(),
                notification.getDescription(),
                new UserDto(
                    user.getId(), 
                    user.getName(), 
                    user.getEdv(), 
                    user.getEmail(), 
                    user.getNumber(), 
                    imageServ.toUrl(user.getImage()),
                    user.getEts()
                )
                ));
        }
        return new ListPageDto<>(pages, newList);
    }
    
}
