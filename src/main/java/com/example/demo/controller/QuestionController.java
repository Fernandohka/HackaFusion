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
import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.Token;
import com.example.demo.dto.Web.CreateQuestionDto;
import com.example.demo.dto.Web.MessageDto;
import com.example.demo.services.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
    
    @Autowired
    QuestionService questionService;

    @PostMapping
    public ResponseEntity<MessageDto> create(@RequestAttribute("token") Token token, @RequestBody CreateQuestionDto data){
        var res = questionService.create(token.getId(), data.idForum(), data.title(), data.description());
        if(res == null)
            return new ResponseEntity<>(new MessageDto("Erro ao criar questão"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new MessageDto("Questão criada com sucesso"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ListPageDto<QuestionDto>> getAllByForum(@RequestParam Long idForum, @RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "0") Integer size){
        var res = questionService.getAllByForum(idForum, page, size);
        System.out.println(res);
        if(res == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getById(@PathVariable Long id, @RequestParam(defaultValue = "0") Integer answerPage,@RequestParam(defaultValue = "0") Integer answerSize){
        var res = questionService.getById(id, answerPage, answerSize);
        if(res == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@RequestAttribute("token") Token token, @PathVariable Long id){
        var res = questionService.delete(token.getId(), id);
        return new ResponseEntity<>(new MessageDto(res.response()), res.success() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
