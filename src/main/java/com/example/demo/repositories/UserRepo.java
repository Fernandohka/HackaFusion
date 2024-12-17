package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.edv = :loginValue or u.email = :loginValue")
    List<User> FindByLogin(@Param("loginValue") String loginValue);

    List<User> findByEdv(String edv);
    List<User> findByEmail(String email);

    List<User> findByNameContainsOrEmailContainsOrEdvContains(String name, String email, String edv);
}
