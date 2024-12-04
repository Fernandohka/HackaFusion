package com.example.demo.services.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.services.BcryptService;

public class BcryptImpl implements BcryptService{

    @Override
    public String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public boolean checkEncode(String password, String encodePassword) {
        return new BCryptPasswordEncoder().matches(password, encodePassword);
    }
}
