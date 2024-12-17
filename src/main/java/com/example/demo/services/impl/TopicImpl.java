package com.example.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.TopicDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.Web.MessageDtoPriv;
import com.example.demo.model.Topic;
import com.example.demo.repositories.TopicRepository;
import com.example.demo.services.ImageStorageService;
import com.example.demo.services.TopicService;

public class TopicImpl implements TopicService {

    @Autowired
    TopicRepository topicRepo;

    @Autowired
    ImageStorageService imageServ;

    @Override
    public TopicDto create(String name, String description) {
        if(name == null || name.equals("") || description == null || description.equals(""))
            return null;
        
        Topic topic = new Topic();
        topic.setName(name);
        topic.setDescription(description);
        topicRepo.save(topic);

        return new TopicDto(
            topic.getId(), 
            topic.getName(),
            topic.getDescription()
            );
    }

    @Override
    public ListPageDto<TopicDto> getAll(Integer page, Integer size) {
        if(page == null || size == null)
            return null;

        var listTopic = topicRepo.findAll();
        var newList = new ArrayList<TopicDto>();

        Integer start = 0;
        Integer end = listTopic.size();
        Integer pages = size>0?(int)Math.ceilDiv(listTopic.size(), size):0;
        
        if(size > 0 && page > 0){
            start = (page-1)*size;
            if(start >= listTopic.size())
                return new ListPageDto<>(pages, newList);
            end = start+size<listTopic.size()?start+size:listTopic.size();
        }

        Topic topic;
        for(int i=start;i<end;i++){
            topic = listTopic.get(i);

            newList.add(new TopicDto(
                topic.getId(),
                topic.getName(),
                topic.getDescription()
                ));
        }
        return new ListPageDto<>(pages, newList);
    }

    @Override
    public TopicDto getById(Long idTopic) {
        try {
            var topic = topicRepo.findById(idTopic).get();

            return new TopicDto(
                topic.getId(),
                topic.getName(),
                topic.getDescription()
                );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ResponseDto delete(Long idTopic) {
        try {
            topicRepo.deleteById(idTopic);
            return new ResponseDto(true, "Tópico deletado com sucesso");
        } catch (Exception e) {
            return new ResponseDto(false, "Erro ao deletar tópico");
        }
    }

    @Override
    public ListPageDto<MessageDtoPriv> getMessageById(Long idTopic) {
        var topicOp = topicRepo.findById(idTopic);
        if(!topicOp.isPresent())
            return null;
        var topic = topicOp.get();
        var messages = new ArrayList<>(topic.getMessages());
        var list = new ArrayList<MessageDtoPriv>();
        for (var messageTopic : messages) {
            var user = messageTopic.getUser();

            list.add(new MessageDtoPriv(
                new UserDto(user.getId(), user.getName(), user.getEdv(), user.getEmail(), user.getNumber(),imageServ.toUrl(user.getImage()), user.getEts(), user.isAdmin()),
                messageTopic.getDescription(),
                messageTopic.getTimestamp())
            );
        }

        return new ListPageDto<>(0, list);
    }
    
}
