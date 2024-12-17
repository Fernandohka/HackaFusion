package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    
}