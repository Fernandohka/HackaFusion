package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MessageProject;

public interface MessageProjectRepository extends JpaRepository<MessageProject, Long> {
    
}