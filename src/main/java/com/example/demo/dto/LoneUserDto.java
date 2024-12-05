package com.example.demo.dto;

public record LoneUserDto(
    String name,
    String edv,
    String email,
    String Telefone,
    byte[] image,
    AbilityDto[] abilitys,
    CarrerDto[] carrers
) {}