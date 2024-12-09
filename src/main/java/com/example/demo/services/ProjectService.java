package com.example.demo.services;

import java.time.LocalDateTime;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.ResponseDto;

public interface ProjectService {
    public ProjectDto create(String name, String description, Boolean status, LocalDateTime startDate, LocalDateTime endDate, Long idCategory, Long idUser);
    public ResponseDto addUser(Long idProject, Long idUser);
    public ResponseDto deleteUser(Long idProject, Long idUser);
    public ListPageDto<ProjectDto> getAll(Integer page, Integer size, String query);
    public ProjectDto getById(Long idProject);
    public ListPageDto<ProjectDto> getByUser(Long idUser, Integer page, Integer size);
}