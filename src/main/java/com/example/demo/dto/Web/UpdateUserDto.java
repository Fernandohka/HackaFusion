package com.example.demo.dto.Web;
import org.springframework.web.multipart.MultipartFile;

public record UpdateUserDto(
    String name,
    String email,
    String EDV,
    String phone,
    Boolean student,
    MultipartFile image
) {}