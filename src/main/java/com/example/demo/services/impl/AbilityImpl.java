package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.AbilityDto;
import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Ability;
import com.example.demo.repositories.AbilityRepo;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.AbilityService;

public class AbilityImpl  implements AbilityService{

    @Autowired
    AbilityRepo repo;

    @Autowired
    UserRepo userRepo;
    

    @Override
    public Ability create(String name) {
        var newAbility = new Ability();
        newAbility.setName(name);

        return  repo.save(newAbility);
    }

    @Override
    public ListPageDto<AbilityDto> getAll(Integer page, Integer size) {
        var listAbility = repo.findAll();
        var listDto = new ArrayList<AbilityDto>();

        Integer start = 0;
        Integer end = listAbility.size();
        Integer pages = (int)Math.floor(listAbility.size()/size);


        if (size > 0 || page > 0) {
            start = (size - 1) * page;
            if (start >= listAbility.size())
                return new ListPageDto<>(pages, listDto);
            end = start + size < listDto.size() ? start + size : listDto.size();
        }

        for (int i = start; i < end; i++) {
            var ability = listAbility.get(i);
            listDto.add(new AbilityDto(ability.getName()));
        }

        return new ListPageDto<>(pages, listDto);
    }

    @Override
    public ResponseDto delete(Long idAbility) {
        var opAbility = repo.findById(idAbility);
        if(opAbility.isEmpty())
            return new ResponseDto(false, "habilidade não encontrada");
        repo.delete(opAbility.get());
        return new ResponseDto(true, "Deletado com sucesso!");
    }

    @Override
    public ListPageDto<AbilityDto> getAllByUser(Long idUser, Integer page, Integer size) {
        var userOp = userRepo.findById(idUser);

        if(userOp.isEmpty())
            return null;
        
        var listAbility = new ArrayList<>(userOp.get().getAbilitys());
        var listDto = new ArrayList<AbilityDto>();
        

        Integer start = 0;
        Integer end = listAbility.size();
        Integer pages = (int)Math.floor(listAbility.size()/size);


        if (size > 0 || page > 0) {
            start = (size - 1) * page;
            if (start >= listAbility.size())
                return new ListPageDto<>(pages, listDto);
            end = start + size < listDto.size() ? start + size : listDto.size();
        }

        for (int i = start; i < end; i++) {
            var ability = listAbility.get(i);
            listDto.add(new AbilityDto(ability.getName()));
        }

        return new ListPageDto<>(pages, listDto);

    }

}