package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    
}