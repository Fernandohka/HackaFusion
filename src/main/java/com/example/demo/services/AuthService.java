package com.example.demo.services;

import com.example.demo.dto.ResponseEntityDto;
import com.example.demo.model.User;

public interface AuthService {
    public ResponseEntityDto<User> login(String login, String password);
    public Boolean validateEdvIsFree(String edv);
    public Boolean validateEdv(String edv);
    public Boolean validateEmailIsFree(String email);
    public Boolean validateEmail(String email);
    public Boolean ValidatePassword(String password);
}
