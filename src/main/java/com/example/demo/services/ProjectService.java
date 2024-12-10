package com.example.demo.services;

import java.time.LocalDateTime;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.ResponseDto;

public interface ProjectService {
    public ProjectDto create(String name, String description, LocalDateTime startDate, LocalDateTime endDate, Long idCategory, Long idUser);
    public ResponseDto addUser(Long idUser, Long idProject, Long idAddUser);
    public ResponseDto deleteUser(Long idUser, Long idProject, Long idDeleteUser);
    public ListPageDto<ProjectDto> getAll(Integer page, Integer size, String query);
    public ProjectDto getById(Long idProject);
    public ListPageDto<ProjectDto> getByUser(Long idUser, Integer page, Integer size);
}