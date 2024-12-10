package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Token;
import com.example.demo.dto.Web.CreateAnswerDto;
import com.example.demo.dto.Web.MessageDto;
import com.example.demo.services.AnswerService;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    
    @Autowired
    AnswerService answerService;

    @PostMapping
    public ResponseEntity<MessageDto> create(@RequestAttribute("token") Token token, @RequestBody CreateAnswerDto data){
        var res = answerService.create(token.getId(), data.idQuestion(), data.description());
        if(res == null)
            return new ResponseEntity<>(new MessageDto("Erro ao criar resposta"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new MessageDto("Resposta criada com sucesso"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@RequestAttribute("token") Token token, @PathVariable Long id){
        var res = answerService.delete(token.getId(), id);
        return new ResponseEntity<>(new MessageDto(res.response()), res.success() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/up/{idAnswer}")
    public ResponseEntity<MessageDto> addUpVote(@RequestAttribute("token") Token token, @PathVariable Long idAnswer){
        var res = answerService.addVote(true, token.getId(), idAnswer);
        if(res == null)
            return new ResponseEntity<>(new MessageDto("Erro ao criar questão"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new MessageDto("Questão criada com sucesso"), HttpStatus.OK);
    }

    @PostMapping("/down/{idAnswer}")
    public ResponseEntity<MessageDto> addDownVote(@RequestAttribute("token") Token token, @PathVariable Long idAnswer){
        var res = answerService.addVote(false, token.getId(), idAnswer);
        if(res == null)
            return new ResponseEntity<>(new MessageDto("Erro ao criar questão"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new MessageDto("Questão criada com sucesso"), HttpStatus.OK);
    }

}
