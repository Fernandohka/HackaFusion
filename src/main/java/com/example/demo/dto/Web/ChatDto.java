package com.example.demo.dto.Web;

import com.example.demo.dto.UserDto;

public record ChatDto(
    UserDto user,
    Long id
) {}