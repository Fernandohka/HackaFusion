package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Ability;

public interface AbilityService {
    public Ability post(String image, String name);
    public List<Ability> getAll(Integer page, Integer size);
    public ResponseDto delete(Long idAbility);
}
