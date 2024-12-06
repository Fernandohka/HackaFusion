package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Token;
import com.example.demo.dto.Web.LoginDto;
import com.example.demo.dto.Web.ReturnLoginDto;
import com.example.demo.services.AuthService;
import com.example.demo.services.JWTService;

@RestController
@RequestMapping("/auth")
public class Auth {
    
    @Autowired
    AuthService service;

    @Autowired 
    JWTService<Token> jwt;

    @PostMapping()
    public  ResponseEntity<ReturnLoginDto> login(@RequestBody LoginDto data){
        var curUser = service.login(data.login(), data.password());
        if(curUser==null)
            return new ResponseEntity<>(new ReturnLoginDto(null,"Usuario não encontrado",false),HttpStatus.BAD_REQUEST);

        var token = new Token();
        token.setAdmin(curUser.isAdmin());
        token.setId(curUser.getId());
        
        return new ResponseEntity<>(new ReturnLoginDto(jwt.get(token),"Usuario cadastrado com sucesso!",curUser.isAdmin()),HttpStatus.OK);
    }
}
