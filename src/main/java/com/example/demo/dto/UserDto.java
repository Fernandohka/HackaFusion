package com.example.demo.dto;

public record UserDto(
    String name,
    String edv,
    String email,
    String Telefone,
    byte[] image
) {}