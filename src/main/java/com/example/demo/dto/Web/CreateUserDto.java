package com.example.demo.dto.Web;

public record CreateUserDto(
    String name,
    String email,
    String EDV,
    String phone,
    String password
) {}
