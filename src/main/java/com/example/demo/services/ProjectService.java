package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Project;

public interface ProjectService {
    public Project post(String name, String description, Boolean status, LocalDateTime startDate, LocalDateTime endDate, String category);
    public ResponseDto addUser(Long idProject, Long idUser);
    public ResponseDto deleteUser(Long idProject, Long idUser);
    public List<Project> getAll(Integer page, Integer size);
    public Project getById(Long idProject);
    public List<Project> getByUser(Long idUser);
}