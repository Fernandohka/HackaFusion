package com.example.demo.services;

import java.util.List;

public interface AbilityService {
    public Ability post(String image, String name);
    public List<Ability> getAll(Integer page, Integer size);
    public ResponseDto delete(Long idAbility);
}
