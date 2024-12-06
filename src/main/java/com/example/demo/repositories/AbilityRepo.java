package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Ability;

public interface AbilityRepo extends JpaRepository<Ability,Long>{
    
} 