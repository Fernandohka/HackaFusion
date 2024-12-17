package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.Token;
import com.example.demo.dto.Web.ChatDto;
import com.example.demo.dto.Web.MessageDto;
import com.example.demo.dto.Web.MessageDtoPriv;
import com.example.demo.dto.Web.NewMessageDto;
import com.example.demo.services.ChatService;

@RestController
public class ChatPriv {

    @Autowired
    ChatService service;

    @PostMapping("/chat/user/{id}")
    public ResponseEntity<MessageDto> CreateChat(@RequestAttribute("token") Token token,@PathVariable Long id){
        try{
            if(service.create(token.getId(),id)==null)
                return new ResponseEntity<>(new MessageDto("Erro ao criar chat"),HttpStatus.BAD_REQUEST);
            
            return new ResponseEntity<>(new MessageDto("Chat criado com sucesso"),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDto("Erro ao criar chat"),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/chat")
    public ResponseEntity<ListPageDto<ChatDto>> getChat(@RequestAttribute("token") Token token,@RequestParam(defaultValue = "") String query){
        try {
            var list = service.getAllByUser(token.getId(),query);
            if(list == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                
            return new ResponseEntity<>(list,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/chat/{id}")
    public ResponseEntity<ListPageDto<MessageDtoPriv>> getChat(@RequestAttribute("token") Token token,@PathVariable Long id){
        try {
            var list = service.getMessage(token.getId(), id);
            if(list == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                
            return new ResponseEntity<>(list,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/chat/message")
    public ResponseEntity<MessageDto> creteMessage(@RequestAttribute("token") Token token,@RequestBody NewMessageDto data){

        System.out.println("AQUI");
        var res = service.createMessage(token.getId(), data.chatId(), data.description());
        if(!res.success())
            return new ResponseEntity<>(new MessageDto(res.response()),HttpStatus.BAD_REQUEST);
        
        System.out.println("Vai dar boa!");

        return new ResponseEntity<>(new MessageDto("Mensagem criada com sucesso"),HttpStatus.OK);
    }
    
}
