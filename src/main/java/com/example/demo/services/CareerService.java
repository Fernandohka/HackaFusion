package com.example.demo.services;

import java.util.List;

public interface CareerService {
    public Career post(String name);
    public ResponseDto delete(Long idCareer);
    public List<Career> getAll(Integer page, Integer size);
}
