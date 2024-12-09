package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.ImageStorageService;
import com.example.demo.services.UserService;
import com.example.demo.util.ImageUtils;

public class UserImpl implements UserService {

    @Autowired
    UserRepo repo;

    @Autowired
    BcryptImpl crypt;

    @Autowired
    ImageStorageService imageServ;

    @Override
    public User create(String nome, String edv, String email, String password, String numero) {
        try {
            var newUser = new User();
            newUser.setName(nome);
            newUser.setEdv(edv);
            newUser.setEmail(email);
            newUser.setPassword(crypt.encode(password));
            newUser.setNumber(numero);
            newUser.setAdmin(false);
            newUser.setEts(false);
            newUser.setImage(imageServ.UploadImage(ImageUtils.compressImage(Files.readAllBytes(Paths.get("C:\\Users\\disrct\\Desktop\\HACKAFUSION\\HackaFusion\\src\\main\\java\\com\\example\\demo\\images\\user.png"))))); 
            newUser.setDescription("");
            return repo.save(newUser);
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    @Override
    public ListPageDto<UserDto> getAll(Integer page, Integer size, String query) {
        List<User> listUser;
        if(query == null || query.equals(""))
            listUser = repo.findAll();
        else
            listUser = repo.findByNameOrEmailOrEdv(query, query, query);

        Integer start = 0;
        Integer end = listUser.size();
        Integer pages = size>0?(int)Math.floor(listUser.size()/size):0;

        var newList = new ArrayList<UserDto>();

        if (size > 0 && page > 0) {
            start = (page - 1) * size;
            if (start >= listUser.size())
                return new ListPageDto<>(pages, newList);
            end = start + size < listUser.size() ? start + size : listUser.size();
        }

        for (int i = start; i < end; i++) {
            var user = listUser.get(i);
            newList.add(new UserDto(user.getId(),user.getName(), user.getEdv(), user.getEmail(),user.getNumber(),imageServ.toUrl(user.getImage())));
        }

        return new ListPageDto<>(pages, newList);
    }

    @Override
    public UserDto getById(Long idUser) {
        var opUser = repo.findById(idUser);
        if (!opUser.isPresent())
            return null;
        var user = opUser.get();
        return new UserDto(user.getId(),user.getName(), user.getEdv(), user.getEmail(), user.getNumber(), imageServ.toUrl(user.getImage()));
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

            user.setName(nome!=null?nome:user.getName());
            user.setEdv(edv!=null?edv:user.getEdv());
            user.setEmail(email!=null?email:user.getEmail());
            user.setPassword(password!=null?crypt.encode(password):user.getPassword());
            user.setNumber(numero!=null?numero:user.getNumber());
            user.setAdmin(false);
            user.setEts(false);
            user.setDescription(description!=null?description:user.getDescription());
            if(image!=null){
                var res = imageServ.UpdateImage(user.getImage(), image);
                if (!res.success()) {
                    return res;
                }
            }
            repo.save(user);

            return new ResponseDto(true, "Usuario modificado usuario!!");

        } catch (Exception e) {
            return new ResponseDto(false, "Erro ao modificar usuario!!");
        }

    }

}
