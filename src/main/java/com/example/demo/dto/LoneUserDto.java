package com.example.demo.dto;

import com.example.demo.dto.Web.AbilityDto;

public record LoneUserDto(
    String name,
    String edv,
    String email,
    String Telefone,
    byte[] image,
    AbilityDto[] abilitys,
    CarrerDto[] carrers
) {}