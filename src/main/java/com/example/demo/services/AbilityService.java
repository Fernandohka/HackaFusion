package com.example.demo.services;


import com.example.demo.dto.AbilityDto;
import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Ability;

public interface AbilityService {
    public Ability create(String name);
    public ListPageDto<AbilityDto> getAll(Integer page, Integer size);
    public ListPageDto<AbilityDto> getAllByUser(Long idUser,Integer page, Integer size);
    public ResponseDto delete(Long idAbility);
    public ResponseDto addAbility(Long idUser, Long idAbility);
    public ResponseDto deleteAbility(Long idUser, Long idAbility);
}
