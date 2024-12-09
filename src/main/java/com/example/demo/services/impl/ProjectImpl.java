package com.example.demo.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Category;
import com.example.demo.model.Project;
import com.example.demo.model.User;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.ProjectService;

public class ProjectImpl implements ProjectService {

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    ProjectRepository projectRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public ProjectDto create(String name, String description, Boolean status, LocalDateTime startDate, LocalDateTime endDate, Long idCategory, Long idUser) {
        if(name == null || name.equals("") || description == null || description.equals("") || status == null || startDate == null || endDate == null || idCategory == null)
            return null;

        Category category;
        
        try {
            category = categoryRepo.findById(idCategory).get();
        } catch (Exception e) {
            return null;
        }
        
        Project project = new Project();
        project.setCategory(category);
        project.setDescription(description);
        project.setEndDate(endDate);
        project.setName(name);
        project.setStartDate(startDate);
        project.setStatus(status);
        projectRepo.save(project);
        addUser(project.getId(), idUser);

        return new ProjectDto(
            project.getId(), 
            project.getName(),
            project.getDescription(),
            project.isStatus(),
            project.getStartDate(),
            project.getEndDate(),
            category.getName());
    }

    @Override
    public ResponseDto addUser(Long idProject, Long idUser) {
        if(idProject == null || idUser == null)
            return new ResponseDto(false, "Erro ao adicionar usuario");
        
        User user;
        Project project;

        try {
            user = userRepo.findById(idUser).get();
            project = projectRepo.findById(idUser).get();
        } catch (Exception e) {
            return new ResponseDto(false, "Erro ao adicionar usuario");
        }

        var users = project.getUsers();
        users.add(user);

        return new ResponseDto(true, "Usuario adicionado com sucesso");
    }

    @Override
    public ResponseDto deleteUser(Long idProject, Long idUser) {
        if(idProject == null || idUser == null)
            return new ResponseDto(false, "Erro ao deletar usuario");
        
        User user;
        Project project;

        try {
            user = userRepo.findById(idUser).get();
            project = projectRepo.findById(idUser).get();
        } catch (Exception e) {
            return new ResponseDto(false, "Erro ao deletar usuario");
        }

        var users = project.getUsers();
        users.remove(user);

        return new ResponseDto(true, "Usuario deletado com sucesso");
    }

    @Override
    public ListPageDto<ProjectDto> getAll(Integer page, Integer size) {
        if(page == null || size == null || page < 1 || size < 1)
            return null;

        var listProject = projectRepo.findAll();
        var newList = new ArrayList<ProjectDto>();

        Integer start = 0;
        Integer end = listProject.size();
        Integer pages = (int)Math.floor(listProject.size()/size);
        
        if(size > 0 || page > 0){
            start = (page-1)*size;
            if(start >= listProject.size())
                return new ListPageDto<>(pages, newList);
            end = start+size<listProject.size()?start+size:listProject.size();
        }

        Project project;
        for(int i=start;i<end;i++){
            project = listProject.get(i);

            newList.add(new ProjectDto(project.getId(),
                project.getName(),
                project.getDescription(),
                project.isStatus(),
                project.getStartDate(),
                project.getEndDate(),
                project.getCategory().getName()
                ));
        }
        return new ListPageDto<>(pages, newList);
    }

    @Override
    public ProjectDto getById(Long idProject) {
        try {
            var project = projectRepo.findById(idProject).get();

            return new ProjectDto(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.isStatus(),
                project.getStartDate(),
                project.getEndDate(),
                project.getCategory().getName()
                );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ListPageDto<ProjectDto> getByUser(Long idUser, Integer page, Integer size) {
        if(page == null || size == null || page < 1 || size < 1)
            return null;

        var listProject = new ArrayList<>(userRepo.findById(idUser).get().getProjects());
        var newList = new ArrayList<ProjectDto>();

        Integer start = 0;
        Integer end = listProject.size();
        Integer pages = (int)Math.floor(listProject.size()/size);
        
        if(size > 0 && page > 0){
            start = (page-1)*size;
            if(start >= listProject.size())
                return new ListPageDto<>(pages, newList);
            end = start+size<listProject.size()?start+size:listProject.size();
        }

        Project project;
        for(int i=start;i<end;i++){
            project = listProject.get(i);
            newList.add(new ProjectDto(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.isStatus(),
                project.getStartDate(),
                project.getEndDate(),
                project.getCategory().getName()
                ));
        }
        return new ListPageDto<>(pages, newList);
    }
}