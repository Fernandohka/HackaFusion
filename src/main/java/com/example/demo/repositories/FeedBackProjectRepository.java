package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.FeedBackProject;

public interface FeedBackProjectRepository extends JpaRepository<FeedBackProject, Long> {
    
}