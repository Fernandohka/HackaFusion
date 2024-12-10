package com.example.demo.services;


import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.Web.CarrerDto;
import com.example.demo.model.Carrer;

public interface CareerService {
    public Carrer create(String name);
    public ResponseDto delete(Long idCareer);
    public ListPageDto<CarrerDto> getAll(Integer page, Integer size,String query);
    public ListPageDto<CarrerDto> getAllByUser(Long idUser,Integer page, Integer size);
    public ResponseDto addCareer(Long idUser,Long idCareer);
    public ResponseDto deleteCareer(Long idUser,Long idCareer);

}
