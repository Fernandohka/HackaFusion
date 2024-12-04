package com.example.demo.services;

public interface AbilityService {
    public Ability post(String image, String name);
    public ResponseDto getAll();
    public ResponseDto delete(Long idAbility);
}
