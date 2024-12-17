package com.example.demo.dto;

public record UserDto(
    Long id,
    String name,
    String edv,
    String email,
    String telefone,
    String image,
    Boolean student
) {}