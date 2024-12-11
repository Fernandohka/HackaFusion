package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.Token;
import com.example.demo.dto.TopicDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.Web.AnwserProfileDto;
import com.example.demo.dto.Web.CreateUserDto;
import com.example.demo.dto.Web.MessageDto;
import com.example.demo.dto.Web.QuestProfileDto;
import com.example.demo.dto.Web.ReturnProfiledto;
import com.example.demo.dto.Web.UpdatePassDto;
import com.example.demo.dto.Web.UpdateUserDto;
import com.example.demo.services.AuthService;
import com.example.demo.services.UserService;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    AuthService auth;

    @PostMapping("/user")
    public ResponseEntity<MessageDto> createUser(@RequestBody CreateUserDto data){

        if(!auth.validateEdvIsFree(data.EDV()))
            return new ResponseEntity<>(new MessageDto("EDV ja Cadastrado!"),HttpStatus.BAD_REQUEST);

        if(!auth.validateEdv(data.EDV()))
            return new ResponseEntity<>(new MessageDto("EDV invalido!"),HttpStatus.BAD_REQUEST);
        
        if(!auth.validateEmailIsFree(data.email()))
            return new ResponseEntity<>(new MessageDto("Email  ja Cadastrado!"),HttpStatus.BAD_REQUEST);

        if(!auth.validateEmail(data.email()))
            return new ResponseEntity<>(new MessageDto("Email invalido!"),HttpStatus.BAD_REQUEST); 
        
        if(!auth.ValidatePassword(data.password()))
            return new ResponseEntity<>(new MessageDto("Senha invalida!Deve conter Numero,Letra maiscula e miniscula"),HttpStatus.BAD_REQUEST);

        if(service.create(data.name(), data.EDV(), data.email(), data.password(),data.phone())==null)
            return new ResponseEntity<>(new MessageDto("Errou ao criar usuario"),HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new MessageDto("Usuario criado com sucesso!"),HttpStatus.OK); 
    }


    @GetMapping("/user")
    public  ResponseEntity<ListPageDto<UserDto>> getAllUser(@RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "0") Integer size,@RequestParam(defaultValue = "") String query){
        try {
            return new ResponseEntity<>(service.getAll(page, size,query),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{id}")
    public  ResponseEntity<ReturnProfiledto> getUser(@RequestAttribute("token") Token token,@PathVariable Long id){
        
        var user = id!=0?service.getById(id):service.getById(token.getId());
        var isCurrentUser = id==token.getId()?true:false;

        if(user == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new ReturnProfiledto(isCurrentUser,user),HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<MessageDto> updateUser(@RequestAttribute("token") Token token,@ModelAttribute UpdateUserDto data){
        try {
            var res = service.update(token.getId(), data.name(), data.EDV(), data.email(),  null, data.phone(), false, data.student(), data.image(), null);
            
            if(!res.success())
                return new ResponseEntity<>(new MessageDto(res.response()),HttpStatus.BAD_REQUEST);
            
            return new ResponseEntity<>(new MessageDto("Usuario atualizado com sucesso!"),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageDto("Erro ao criar user"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/password")
    public ResponseEntity<MessageDto> updateUser(@RequestAttribute("token") Token token,@ModelAttribute UpdatePassDto data){
        var res = service.updatePass(token.getId(),data.password(),data.newPassword());
        
        if(!res.success())
            return new ResponseEntity<>(new MessageDto(res.response()),HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity<>(new MessageDto("Usuario atualizado com sucesso!"),HttpStatus.OK);
    }


    @DeleteMapping("/admin/user/{id}")
    public ResponseEntity<MessageDto> deleteUser(@RequestAttribute("token") Token token,@PathVariable Long id){

        if(!token.isAdmin()){
            return new ResponseEntity<>(new MessageDto("Usuario sem autorização"),HttpStatus.UNAUTHORIZED);
        }

        var res = service.delete(id);

        if(!res.success())
            return new ResponseEntity<>(new MessageDto(res.response()),HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity<>(new MessageDto("Usuario deletado com sucesso"),HttpStatus.OK);
        
    }

    @GetMapping("/user/interactions/question/{id}")
    public ResponseEntity<ListPageDto<QuestProfileDto>> getInteractionQuest(@RequestAttribute("token") Token token,@PathVariable Long id,@RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "0") Integer size){
        try {
            var res = service.interactionQuest(id, page, size);
    
            if(res == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            
            return new ResponseEntity<>(res,HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/interactions/anwser/{id}")
    public ResponseEntity<ListPageDto<AnwserProfileDto>> getInteractionAnwser(@RequestAttribute("token") Token token,@PathVariable Long id,@RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "0") Integer size){
        try {
            var res = service.interactionAnwser(id, page, size);
    
            if(res == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            
            return new ResponseEntity<>(res,HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/interactions/topic/{id}")
    public ResponseEntity<ListPageDto<TopicDto>> getInteractionTopic(@RequestAttribute("token") Token token,@PathVariable Long id,@RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "0") Integer size){
        try {
            var res = service.interactionTopic(id, page, size);
    
            if(res == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            
            return new ResponseEntity<>(res,HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
