package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MessageTopic;

public interface MessageTopicRepository extends JpaRepository<MessageTopic, Long> {
    
}