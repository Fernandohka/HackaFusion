package com.example.demo.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.MessageTopicDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.TopicDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.MessageTopic;
import com.example.demo.model.Topic;
import com.example.demo.model.User;
import com.example.demo.repositories.MessageTopicRepository;
import com.example.demo.repositories.TopicRepository;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.ImageStorageService;
import com.example.demo.services.TopicMessageService;

public class TopicMessageImpl implements TopicMessageService {

    @Autowired
    TopicRepository topicRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    MessageTopicRepository messageTopicRepo;

    @Autowired
    ImageStorageService imageServ;

    @Override
    public MessageTopicDto createMessage(Long idTopic, Long idUser, String description, LocalDateTime timestamp) {
        if(description == null || description.equals("") || idTopic == null || idUser == null || timestamp == null)
            return null;

        Topic topic;
        User user;
        
        try {
            topic = topicRepo.findById(idTopic).get();
            user = userRepo.findById(idUser).get();
        } catch (Exception e) {
            return null;
        }
        
        MessageTopic messageTopic = new MessageTopic();
        messageTopic.setTopic(topic);
        messageTopic.setUser(user);
        messageTopic.setDescription(description);
        messageTopic.setTimestamp(timestamp);
        messageTopicRepo.save(messageTopic);

        return new MessageTopicDto(
            messageTopic.getId(), 
            new TopicDto(
                topic.getId(), 
                topic.getName(), 
                topic.getDescription()
                ),
            new UserDto(
                user.getId(), 
                user.getName(), 
                user.getEdv(), 
                user.getEmail(), 
                user.getNumber(), 
                imageServ.toUrl(user.getImage()),
                user.getEts()
                ),
            messageTopic.getDescription(),
            messageTopic.getTimestamp()
            );
    }

    @Override
    public ResponseDto deleteMessage(Long idTopicMessage) {
        try {
            messageTopicRepo.deleteById(idTopicMessage);
            return new ResponseDto(true, "Mensagem deletada com sucesso");
        } catch (Exception e) {
            return new ResponseDto(false, "Erro ao deletar mensagem");
        }
    }
    
}
