package com.example.demo.services;

public interface CareerService {
    public Career post(String name);
    public ResponseDto delete(Long idCareer);
    public ResponseDto getAll(Integer page, Integer size);
}
