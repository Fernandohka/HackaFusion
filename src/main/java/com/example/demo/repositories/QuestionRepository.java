package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    public List<Question> findByForumId(Long id);
}