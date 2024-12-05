package com.example.demo.services;

import com.example.demo.model.User;

public interface AuthService {
    public User login(String login, String password);
    public Boolean validateEdvIsFree(String edv);
    public Boolean validateEdv(String edv);
    public Boolean validateEmailIsFree(String email);
    public Boolean validateEmail(String email);
    public Boolean ValidatePassword(String password);
}
