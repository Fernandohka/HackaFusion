package com.example.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.ResponseDto;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.UserService;

public class UserImpl implements UserService{

    @Autowired
    UserRepo repo;

    @Override
    public User create(String nome, String edv, String email, String password, String numero, Boolean admin,Boolean ets, String image, String description) {
        
    }

    @Override
    public List<User> getAll(Integer page, Integer size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public User getById(Long idUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public ResponseDto delete(Long idUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ResponseDto update(Long idUser, String nome, String edv, String email, String password, String numero,
            Boolean admin, Boolean ets, String image, String description) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
