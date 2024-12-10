package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.Web.CarrerDto;
import com.example.demo.model.Carrer;
import com.example.demo.repositories.CarrerRepo;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.CareerService;

public class CarrerImpl implements CareerService{

    @Autowired 
    CarrerRepo repo;

    @Autowired
    UserRepo userRepo;

    @Override
    public Carrer create(String name) {
        var newCar = new Carrer();
        newCar.setName(name);
        
        return repo.save(newCar);
    }

    @Override
    public ResponseDto delete(Long idCareer) {
        var careerOp = repo.findById(idCareer);

        if(!careerOp.isPresent())
            return new ResponseDto(false,"Carreira não encontrado!");
        
        var career = careerOp.get();
        
        repo.delete(career);
        
        return new ResponseDto(true,"Deletado com sucesso!");
    }

    @Override
    public ListPageDto<CarrerDto> getAll(Integer page, Integer size,String query) {

        List<Carrer> listCarrer;

        if(query==""|| query==null){
            listCarrer = repo.findAll();
        }else{
            listCarrer = repo.findByNameContains(query);
        }

        var listDto = new ArrayList<CarrerDto>();

        Integer start = 0;
        Integer end = listCarrer.size();
        Integer pages = size>0?(int)Math.floor(listCarrer.size()/size):0;


        if (size > 0 || page > 0) {
            start = (size - 1) * page;
            if (start >= listCarrer.size())
                return new ListPageDto<>(pages, listDto);
            end = start + size < listDto.size() ? start + size : listDto.size();
        }

        for (int i = start; i < end; i++) {
            var ability = listCarrer.get(i);
            listDto.add(new CarrerDto(ability.getName(),ability.getId()));
        }

        return new ListPageDto<>(pages, listDto);
    }

    @Override
    public ResponseDto addCareer(Long idUser, Long idCareer) {
        var userOp = userRepo.findById(idUser);
        var careerOp = repo.findById(idCareer);

        if(userOp.isEmpty())
            return new ResponseDto(false, "Usuario não encontrado!!");

        if(careerOp.isEmpty())
            return new ResponseDto(false, "Carreira não encontrado!!");

        var currUser =  userOp.get();
        var currCareer = careerOp.get();

        currUser.getCarrers().add(currCareer);

        userRepo.save(currUser);

        return new ResponseDto(true,"Adicionado com sucesso!");
    }

    @Override
    public ResponseDto deleteCareer(Long idUser, Long idCareer) {
        var userOp = userRepo.findById(idUser);
        var careerOp = repo.findById(idCareer);

        if(userOp.isEmpty())
            return new ResponseDto(false, "Usuario não encontrado!!");

        if(careerOp.isEmpty())
            return new ResponseDto(false, "Carreira não encontrado!!");

        var currUser =  userOp.get();
        var currCareer = careerOp.get();

        if(!currUser.getCarrers().remove(currCareer))
            return new ResponseDto(false, "Erro em deletar Carreira!");

        userRepo.save(currUser);

        return new ResponseDto(true,"Adicionado com sucesso!");
    }

    @Override
    public ListPageDto<CarrerDto> getAllByUser(Long idUser,Integer page, Integer size) {

        var userOp = userRepo.findById(idUser);

        if(userOp.isEmpty())
            return null;
        
        var listCarrer = new ArrayList<>(userOp.get().getCarrers());
        var listDto = new ArrayList<CarrerDto>();
        

        Integer start = 0;
        Integer end = listCarrer.size();
        Integer pages = size>0?(int)Math.floor(listCarrer.size()/size):0;


        if (size > 0 && page > 0) {
            start = (size - 1) * page;
            if (start >= listCarrer.size())
                return new ListPageDto<>(pages, listDto);
            end = start + size < listDto.size() ? start + size : listDto.size();
        }

        for (int i = start; i < end; i++) {
            var ability = listCarrer.get(i);
            listDto.add(new CarrerDto(ability.getName(),ability.getId()));
        }

        return new ListPageDto<>(pages, listDto);
    }
    
}
