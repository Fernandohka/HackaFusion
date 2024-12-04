package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Ability;


public interface AbilityRepository extends JpaRepository<Ability, Long>{
    Optional<Ability> findOneByName(String name);
    Ability findOneById(Long id);
}