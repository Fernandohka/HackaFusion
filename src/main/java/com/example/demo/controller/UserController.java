package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.Web.CreateUserDto;
import com.example.demo.services.AuthService;
import com.example.demo.services.UserService;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    AuthService auth;

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDto data){

        if(!auth.validateEdvIsFree(data.EDV()))
            return new ResponseEntity<>("EDV ja Cadastrado!",HttpStatus.BAD_REQUEST);

        if(!auth.validateEdv(data.EDV()))
            return new ResponseEntity<>("EDV invalido!",HttpStatus.BAD_REQUEST);
        
        if(!auth.validateEmailIsFree(data.email()))
            return new ResponseEntity<>("Email  ja Cadastrado!",HttpStatus.BAD_REQUEST);

        if(!auth.validateEmail(data.email()))
            return new ResponseEntity<>("Email invalido!",HttpStatus.BAD_REQUEST); 
        
        if(!auth.ValidatePassword(data.password()))
            return new ResponseEntity<>("Senha invalida!Deve conter Numero,Letra maiscula e miniscula",HttpStatus.BAD_REQUEST);

        if(service.create(data.name(), data.EDV(), data.email(), data.password(),data.phone())==null)
            return new ResponseEntity<>("Errou ao criar usuario",HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("Usuario criado com sucesso!",HttpStatus.OK); 
    }

    @GetMapping("/user")
    public  ResponseEntity<ListPageDto<UserDto>> getAllUser(@RequestParam Integer page,@RequestParam Integer size){
        return new ResponseEntity<>(service.getAll(page, size),HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public  ResponseEntity<UserDto> getUser(@PathVariable Long id){
        var user = service.getById(id);
        if(user == null)
            return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/admin/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){

        var res = service.delete(id);

        if(!res.success())
            return new ResponseEntity<>(res.response(),HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity<>("Usuario deletado com sucesso",HttpStatus.OK);
        
    }


}
