package com.example.demo.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.Image;

public interface ImageStorageService {

    public Image uploadImage(MultipartFile file) throws IOException;
    public byte[] downloadImage(Image image);
}