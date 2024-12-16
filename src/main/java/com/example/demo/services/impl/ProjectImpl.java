package com.example.demo.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public ProjectDto create(String name, String description, Long idCategory, Long idUser) {
        if(name == null || name.equals("") || description == null || description.equals("") || idCategory == null)
            return null;

        Category category;
        Set<User> users;
        
        try {
            category = categoryRepo.findById(idCategory).get();
            users = new HashSet<>();
            users.add(userRepo.findById(idUser).get());
        } catch (Exception e) {
            return null;
        }
        
        Project project = new Project();
        project.setCategory(category);
        project.setDescription(description);
        project.setEndDate(null);
        project.setName(name);
        project.setStartDate(LocalDateTime.now());
        project.setStatus(true);
        project.setUsers(users);
        projectRepo.save(project);

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
    public ResponseDto addUser(Long idUser, Long idProject, Long idAddUser) {
        if(idProject == null || idUser == null || idAddUser == null)
            return new ResponseDto(false, "Erro ao adicionar usuario");
        
        User user;
        Project project;
        User addUser;

        try {
            user = userRepo.findById(idUser).get();
            project = projectRepo.findById(idUser).get();
            addUser = userRepo.findById(idAddUser).get();
        } catch (Exception e) {
            return new ResponseDto(false, "Erro ao adicionar usuario");
        }

        if(!project.getUsers().contains(user))
            return null;
        
        if(!project.isStatus())
            return null;

        var users = project.getUsers();
        users.add(addUser);
        projectRepo.save(project);

        return new ResponseDto(true, "Usuario adicionado com sucesso");
    }

    @Override
    public ResponseDto deleteUser(Long idUser, Long idProject, Long idDeleteUser) {
        if(idProject == null || idUser == null)
            return new ResponseDto(false, "Erro ao deletar usuario");
        
        User user;
        Project project;
        User deleteUser;

        try {
            user = userRepo.findById(idUser).get();
            project = projectRepo.findById(idProject).get();
            deleteUser = userRepo.findById(idDeleteUser).get();
        } catch (Exception e) {
            return new ResponseDto(false, "Erro ao deletar usuario");
        }

        if(!project.getUsers().contains(user))
            return new ResponseDto(false, "Permissão insuficiente");
        
        if(!project.isStatus())
            return new ResponseDto(false, "Projeto já finalizado");

        var users = project.getUsers();
        users.remove(deleteUser);
        projectRepo.save(project);

        return new ResponseDto(true, "Usuario deletado com sucesso");
    }

    @Override
    public ListPageDto<ProjectDto> getAllPublic(Long idUser, Integer page, Integer size, String query) {
        if(page == null || size == null)
            return null;

        List<Project> listProject;
        if(query == null || query.equals("")){
            listProject = projectRepo.findByUsersIdNot(idUser);
        }
        else{
            listProject = projectRepo.findByUsersIdNotAndCategoryNameContaining(idUser, query);
        }
        var newList = new ArrayList<ProjectDto>();

        Integer start = 0;
        Integer end = listProject.size();
        Integer pages = size>0?(int)Math.ceilDiv(listProject.size(), size):0;
        
        if(size > 0 && page > 0){
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
    public ListPageDto<ProjectDto> getAllMy(Long idUser, Integer page, Integer size, String query) {
        if(page == null || size == null)
            return null;

        List<Project> listProject;
        if(query == null || query.equals("")){
            listProject = projectRepo.findByUsersId(idUser);
        }
        else{
            listProject = projectRepo.findByUsersIdAndCategoryNameContaining(idUser, query);
        }
        var newList = new ArrayList<ProjectDto>();

        Integer start = 0;
        Integer end = listProject.size();
        Integer pages = size>0?(int)Math.ceilDiv(listProject.size(), size):0;
        
        if(size > 0 && page > 0){
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
        if(page == null || size == null)
            return null;

        var listProject = new ArrayList<>(userRepo.findById(idUser).get().getProjects());
        var newList = new ArrayList<ProjectDto>();

        Integer start = 0;
        Integer end = listProject.size();
        Integer pages = size>0?(int)Math.ceilDiv(listProject.size(), size):0;
        
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
