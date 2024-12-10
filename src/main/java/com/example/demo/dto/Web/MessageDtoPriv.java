package com.example.demo.dto.Web;

import java.time.LocalDateTime;

import com.example.demo.dto.UserDto;

public record MessageDtoPriv(
    UserDto user,
    String description,
    LocalDateTime timestamp
) {}
