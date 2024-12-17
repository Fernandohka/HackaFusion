package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Carrer;


public interface CarrerRepo extends JpaRepository<Carrer, Long> {
    List<Carrer> findByNameContains(String name);
}
