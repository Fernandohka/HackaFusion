package com.example.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.AuthService;

public class Auth implements AuthService {

    @Autowired
    UserRepo repo;

    @Autowired
    BcryptImpl crypt;

    @Override
    public User login(String login, String password) {
        var listLogin = repo.FindByLogin(login);

        if (listLogin.isEmpty())
            return null;

        var user = listLogin.get(0);

        if (!crypt.checkEncode(password, user.getPassword()))
            return null;

        return user;

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
        var list = repo.findByEmail(email);
        if (list.isEmpty())
            return false;
        var emailUser = list.get(0).getEmail();

        try {
            return !emailUser.split("@")[1].split(".")[1].endsWith(".");
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public Boolean ValidatePassword(String password) {
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
    }

}
