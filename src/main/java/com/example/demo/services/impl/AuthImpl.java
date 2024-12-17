package com.example.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.ResponseEntityDto;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.AuthService;

public class AuthImpl implements AuthService {

    @Autowired
    UserRepo repo;

    @Autowired
    BcryptImpl crypt;

    @Override
    public ResponseEntityDto<User> login(String login, String password) {
        var listLogin = repo.FindByLogin(login);

        if (listLogin.isEmpty())
            return new ResponseEntityDto<User>(null, "Usuário não encontrado");

        var user = listLogin.get(0);

        if (!crypt.checkEncode(password, user.getPassword()))
            return new ResponseEntityDto<User>(null, "Senha inválida");

        return new ResponseEntityDto<User>(user, "Usuário logado com sucesso");

    }

    @Override
    public Boolean validateEdvIsFree(String edv) {
        var list = repo.findByEdv(edv);
        if (list.isEmpty())
            return true;
        return false;
    }

    @Override
    public Boolean validateEdv(String edv) {
        try {
            if (edv.length() < 8)
                return false;
            Integer.parseInt(edv);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean validateEmailIsFree(String email) {
        var list = repo.findByEmail(email);
        if (list.isEmpty())
            return true;
        return false;
    }

    @Override
    public Boolean validateEmail(String email) {
        try {
            return email.split("@")[1].contains(".");
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    @Override
    public Boolean ValidatePassword(String password) {
        try {
            if (password.length() < 8)
                return false;
    
            Boolean letterUp = false;
            Boolean number = false;
            Boolean letter = false;
    
            for (char c : password.toCharArray()) {
                if (Character.isDigit(c))
                    number = true;
                if (Character.isLowerCase(c))
                    letter = true;
                if (Character.isUpperCase(c))
                    letterUp = true;
            }
    
            return number && letter && letterUp;
            
        } catch (Exception e) {
            return false;
        }
    }

}
