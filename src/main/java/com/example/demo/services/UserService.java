package com.example.demo.services;

import java.util.List;

public interface UserService {
    public User post(String nome, String edv, String email, String password, String numero, Boolean admin, Boolean ets, String image, String description);
    public List<User> getAll(Integer page, Integer size);
    public User getById(Long idUser);
    public ResponseDto delete(Long idUser);
    public ResponseDto update(User user);
}