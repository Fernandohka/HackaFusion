package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByCategoryNameContains(String query);
    List<Project> findByUsersId(Long id);
    List<Project> findByUsersIdNot(Long id);
    List<Project> findByUsersIdAndCategoryNameContaining(Long id, String query);
    List<Project> findByUsersIdNotAndCategoryNameContaining(Long id, String query);
}