package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.Web.AbilityDto;
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
    public ListPageDto<AbilityDto> getAll(Integer page, Integer size,String query) {
        List<Ability> listAbility;

        if(query == null || query.equals("")){
            listAbility = repo.findAll();
        }else{
            listAbility = repo.findByNameContains(query);
        }

        var listDto = new ArrayList<AbilityDto>();

        Integer start = 0;
        Integer end = listAbility.size();
        Integer pages = size>0?(int)Math.ceilDiv(listAbility.size(), size):0;


        if (size > 0 || page > 0) {
            start = (page - 1) * size;
            if (start >= listAbility.size())
                return new ListPageDto<>(pages, listDto);
            end = start + size < listAbility.size() ? start + size : listAbility.size();
        }

        for (int i = start; i < end; i++) {
            var ability = listAbility.get(i);
            listDto.add(new AbilityDto(ability.getName(),ability.getId()));
        }

        return new ListPageDto<>(pages, listDto);
    }

    @Override
    public ResponseDto delete(Long idAbility) {
        var opAbility = repo.findById(idAbility);
        if(opAbility.isEmpty())
            return new ResponseDto(false, "habilidade não encontrada");
        
        var userOp = userRepo.findByAbilitysId(idAbility);
        if(!userOp.isEmpty()){
            for(int i = 0; i < userOp.size(); i++){
                deleteAbility(userOp.get(i).getId(), idAbility);
            }
        }
        
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
        Integer pages = size>0?(int)Math.ceilDiv(listAbility.size(), size):0;


        if (size > 0 && page > 0) {
            start = (size - 1) * page;
            if (start >= listAbility.size())
                return new ListPageDto<>(pages, listDto);
            end = start + size < listDto.size() ? start + size : listDto.size();
        }

        for (int i = start; i < end; i++) {
            var ability = listAbility.get(i);
            listDto.add(new AbilityDto(ability.getName(),ability.getId()));
        }

        return new ListPageDto<>(pages, listDto);

    }

    @Override
    public ResponseDto addAbility(Long idUser, Long idAbility) {
        var userOp = userRepo.findById(idUser);
        var abilityOp = repo.findById(idAbility);

        if(userOp.isEmpty())
            return new ResponseDto(false, "Usuario não encontrado!!");

        if(abilityOp.isEmpty())
            return new ResponseDto(false, "Habilidade não encontrada!!");

        var currUser =  userOp.get();
        var currAbility = abilityOp.get();

        currUser.getAbilitys().add(currAbility);

        userRepo.save(currUser);

        return new ResponseDto(true,"Adicionado com sucesso!");
    }

    @Override
    public ResponseDto deleteAbility(Long idUser, Long idAbility) {
        var userOp = userRepo.findById(idUser);
        var abilityOp = repo.findById(idAbility);

        if(userOp.isEmpty())
            return new ResponseDto(false, "Usuario não encontrado!!");

        if(abilityOp.isEmpty())
            return new ResponseDto(false, "Habilidade não encontrada!!");

        var currUser =  userOp.get();
        var currAbility = abilityOp.get();

        if(!currUser.getAbilitys().remove(currAbility))
            return new ResponseDto(false, "Erro em deletar habilidade!");

        userRepo.save(currUser);

        return new ResponseDto(true,"Adicionado com sucesso!");
    }

}
