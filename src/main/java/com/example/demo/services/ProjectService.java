package com.example.demo.services;


import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.ResponseDto;

public interface ProjectService {
    public ProjectDto create(String name, String description, Long idCategory, Long idUser);
    public ResponseDto addUser(Long idUser, Long idProject, Long idAddUser);
    public ResponseDto deleteUser(Long idUser, Long idProject, Long idDeleteUser);
    public ListPageDto<ProjectDto> getAllPublic(Long idUser, Integer page, Integer size, String query);
    public ListPageDto<ProjectDto> getAllMy(Long idUser, Integer page, Integer size, String query);
    public ProjectDto getById(Long idProject);
    public ListPageDto<ProjectDto> getByUser(Long idUser, Integer page, Integer size);
}