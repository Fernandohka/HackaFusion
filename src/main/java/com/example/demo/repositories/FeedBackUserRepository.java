package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.FeedBackUser;

public interface FeedBackUserRepository extends JpaRepository<FeedBackUser, Long> {
    List<FeedBackUser> findByUserReceiverId(Long idUserReceiver);
}