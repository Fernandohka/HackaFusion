package com.example.demo.services;

public interface UserService {
    public User post(String nome, String edv, String email, String password, String numero, Boolean admin, Boolean ets, String image, String description);
    public ResponseDto getAll();
    public ResponseDto getById(Long idUser);
    public ResponseDto delete(Long idUser);
    public ResponseDto update(User user);
}
