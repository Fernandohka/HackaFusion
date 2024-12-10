package com.example.demo.dto.Web;

import com.example.demo.dto.UserDto;

public record ReturnProfiledto(
    boolean isOwner,
    UserDto user
) {}
