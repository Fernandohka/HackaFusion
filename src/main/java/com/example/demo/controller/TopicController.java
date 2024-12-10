package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.Token;
import com.example.demo.dto.TopicDto;
import com.example.demo.dto.Web.CreateTopicDto;
import com.example.demo.dto.Web.CreateTopicMessageDto;
import com.example.demo.dto.Web.MessageDto;
import com.example.demo.services.TopicMessageService;
import com.example.demo.services.TopicService;

@RestController
@RequestMapping("/topic")
public class TopicController {
    
    @Autowired
    TopicService topicService;

    @Autowired
    TopicMessageService topicMessageService;

    @PostMapping
    public ResponseEntity<MessageDto> create(@RequestAttribute("token") Token token, @RequestBody CreateTopicDto data){
        if(!token.isAdmin())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        
        var res = topicService.create(data.name(), data.description());
        if(res == null)
            return new ResponseEntity<>(new MessageDto("Erro ao criar tópico"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new MessageDto("Tópico criada com sucesso"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ListPageDto<TopicDto>> getAll(@RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "0") Integer size){
        var res = topicService.getAll(page, size);
        if(res == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDto> getById(@PathVariable Long id){
        var res = topicService.getById(id);
        if(res == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@RequestAttribute("token") Token token, @PathVariable Long id){
        if(!token.isAdmin())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        
        var res = topicService.delete(id);
        return new ResponseEntity<>(new MessageDto(res.response()), res.success() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/message")
    public ResponseEntity<MessageDto> createMessage(@RequestAttribute("token") Token token, @RequestBody CreateTopicMessageDto data){
        if(!token.isAdmin())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        
        var res = topicMessageService.createMessage(data.idTopic(), token.getId(), data.description(), data.timestamp());
        if(res == null)
            return new ResponseEntity<>(new MessageDto("Erro ao criar mensagem"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new MessageDto("Mensagem criada com sucesso"), HttpStatus.OK);
    }

    @DeleteMapping("/message/{id}")
    public ResponseEntity<MessageDto> deleteMessage(@RequestAttribute("token") Token token, @PathVariable Long id){
        if(!token.isAdmin())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        
        var res = topicMessageService.deleteMessage(id);
        return new ResponseEntity<>(new MessageDto(res.response()), res.success() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
