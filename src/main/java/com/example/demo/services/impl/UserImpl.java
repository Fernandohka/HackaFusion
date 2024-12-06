package com.example.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.ImageStorageService;
import com.example.demo.services.UserService;

public class UserImpl implements UserService {

    @Autowired
    UserRepo repo;

    @Autowired
    BcryptImpl crypt;

    @Autowired
    ImageStorageService imageServ;

    @Override
    public User create(String nome, String edv, String email, String password, String numero, MultipartFile image,
            String description) {
        try {
            var newUser = new User();
            newUser.setName(nome);
            newUser.setEdv(edv);
            newUser.setEmail(email);
            newUser.setPassword(crypt.encode(password));
            newUser.setNumber(numero);
            newUser.setAdmin(false);
            newUser.setEts(false);
            newUser.setImage(imageServ.UploadImage(image));
            newUser.setDescription(description);
            return repo.save(newUser);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ListPageDto<UserDto> getAll(Integer page, Integer size) {
        var listUser = repo.findAll();

        Integer start = 0;
        Integer end = listUser.size();
        Integer pages = (int)Math.floor(listUser.size()/size);

        var newList = new ArrayList<UserDto>();

        if (size > 0 || page > 0) {
            start = (size - 1) * page;
            if (start >= listUser.size())
                return new ListPageDto<UserDto>(pages, newList);
            end = start + size < listUser.size() ? start + size : listUser.size();
        }

        for (int i = start; i < end; i++) {
            var user = listUser.get(i);
            newList.add(new UserDto(user.getId(), user.getName(), user.getEdv(), user.getEmail(), user.getNumber(),
                    imageServ.toUrl(user.getImage())));
        }

        return new ListPageDto<UserDto>(pages, newList);
    }

    @Override
    public UserDto getById(Long idUser) {
        var opUser = repo.findById(idUser);
        if (!opUser.isPresent())
            return null;
        var user = opUser.get();
        return new UserDto(user.getId(), user.getName(), user.getEdv(), user.getEmail(), user.getNumber(),
                imageServ.toUrl(user.getImage()));
    }

    @Override
    public ResponseDto delete(Long idUser) {
        var opUser = repo.findById(idUser);
        if (!opUser.isPresent())
            return new ResponseDto(false, "Usuario não encontrado!!");
        var user = opUser.get();
        repo.delete(user);
        return new ResponseDto(true, "Usuario deletado com sucesso!!");
    }

    @Override
    public ResponseDto update(Long idUser, String nome, String edv, String email, String password, String numero,
            Boolean admin, Boolean ets, MultipartFile image, String description) {
        try {
            var opUser = repo.findById(idUser);
            if (!opUser.isPresent())
                return new ResponseDto(false, "Usuario não encontrado!!");

            var user = opUser.get();

            user.setName(nome);
            user.setEdv(edv);
            user.setEmail(email);
            user.setPassword(crypt.encode(password));
            user.setNumber(numero);
            user.setAdmin(false);
            user.setEts(false);
            user.setDescription(description);
            var res = imageServ.UpdateImage(user.getImage(), image);
            if (!res.success()) {
                return res;
            }
            repo.save(user);

            return new ResponseDto(true, "Usuario modificado usuario!!");

        } catch (Exception e) {
            return new ResponseDto(false, "Erro ao modificar usuario!!");
        }

    }

}
