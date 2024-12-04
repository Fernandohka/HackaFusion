package com.example.demo.services;

import java.time.LocalDateTime;

public interface ProjectService {
    public Project post(String name, String description, Boolean status, LocalDateTime startDate, LocalDateTime endDate, String category);
    public ProjectUser postUser(long idProject, long idUser);
    public ResponseDto deleteUser(long idProject, long idUser);
    public ResponseDto get(long idProject);
}