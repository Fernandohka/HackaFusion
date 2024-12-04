package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.ImageStorageService;
import com.example.demo.services.UserService;

public class UserImpl implements UserService{

    @Autowired
    UserRepo repo;

    @Autowired
    BcryptImpl crypt;

    @Autowired
    ImageStorageService imageServ;



    @Override
    public User create(String nome, String edv, String email, String password, String numero, MultipartFile image, String description) {
        try {
            var newUser = new User();
            newUser.setName(nome);
            newUser.setEdv(edv);
            newUser.setEmail(email);
            newUser.setPassword(crypt.encode(password));
            newUser.setNumber(numero);
            newUser.setAdmin(false);
            newUser.setEts(false);
            newUser.setImage(imageServ.compressImage(image));
            newUser.setDescription(description);
            return repo.save(newUser);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> getAll(Integer page, Integer size) {
        var listUser = repo.findAll();

        Integer start = 0;
        Integer end = listUser.size();

        if(size >0 || page>0){
            start = size*page;
            if(start>=listUser.size())
                return null;
            end = start+size<listUser.size()?start+size:listUser.size();
        }

        var newList = new ArrayList<UserDto>();


        for(int i=0;i<0;i++){

        }

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
