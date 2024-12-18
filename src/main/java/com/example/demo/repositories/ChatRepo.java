package com.example.demo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Chat;

public interface ChatRepo extends JpaRepository<Chat,Long>{
    List<Chat> findByUserAIdOrUserBId(Long idA, Long idB);
}