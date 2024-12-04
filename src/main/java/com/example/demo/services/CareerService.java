package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Carrer;

public interface CareerService {
    public Carrer create(String name);
    public ResponseDto delete(Long idCareer);
    public List<Carrer> getAll(Integer page, Integer size);
}
