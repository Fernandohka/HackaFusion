package com.example.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.TopicDto;
import com.example.demo.model.Topic;
import com.example.demo.repositories.TopicRepository;
import com.example.demo.services.TopicService;

public class TopicImpl implements TopicService {

    @Autowired
    TopicRepository topicRepo;

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
        Integer pages = (int)Math.floor(listTopic.size()/size);
        
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
    
}
