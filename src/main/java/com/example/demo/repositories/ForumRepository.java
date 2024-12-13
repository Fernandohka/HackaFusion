package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Forum;

public interface ForumRepository extends JpaRepository<Forum, Long> {
    List<Forum> findByNameContains(String query);
}