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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ForumDto;
import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.Token;
import com.example.demo.dto.Web.CreateForumDto;
import com.example.demo.dto.Web.MessageDto;
import com.example.demo.services.ForumService;

@RestController("/forum")
public class ForumController {
    
    @Autowired
    ForumService forumService;

    @PostMapping
    public ResponseEntity<MessageDto> create(@RequestAttribute("token") Token token, @RequestBody CreateForumDto data){
        if(!token.isAdmin())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        var res = forumService.create(data.name(), data.description());
        if(res == null)
            return new ResponseEntity<>(new MessageDto("Erro ao criar forum"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new MessageDto("Forum criado com sucesso"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ListPageDto<ForumDto>> getAll(@RequestAttribute("token") Token token, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, @RequestParam(defaultValue = "") String query){
        var res = forumService.getAll(page, size, query);
        if(res == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForumDto> getById(@PathVariable Long id){
        var res = forumService.getById(id);
        if(res == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@RequestAttribute("token") Token token, @PathVariable Long id){
        if(!token.isAdmin())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        
        var res = forumService.delete(id);
        return new ResponseEntity<>(new MessageDto(res.response()), res.success() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
