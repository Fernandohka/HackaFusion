package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Ability;

public interface AbilityRepo extends JpaRepository<Ability,Long>{
    List<Ability> findByNameContains(String name);
    
} 