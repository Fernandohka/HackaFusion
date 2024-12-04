package com.example.demo.services;

import java.time.LocalDateTime;

public interface ProjectService {
    public Project post(String name, String description, Boolean status, LocalDateTime startDate, LocalDateTime endDate, String category);
    public ProjectUser addUser(Long idProject, Long idUser);
    public ResponseDto deleteUser(Long idProject, Long idUser);
    public ResponseDto getAll(Long idProject);
}