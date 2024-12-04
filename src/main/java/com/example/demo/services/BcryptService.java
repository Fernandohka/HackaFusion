package com.example.demo.services;

public interface BcryptService{
    String encode(String password);
    boolean checkEncode(String password,String encodePassword);
}
