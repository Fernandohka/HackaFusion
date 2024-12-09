package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    
}