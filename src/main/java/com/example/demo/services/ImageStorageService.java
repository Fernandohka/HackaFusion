package com.example.demo.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


public interface ImageStorageService {
    public byte[] compressImage(MultipartFile file) throws IOException;
    public byte[] descompressImage(byte[] file);
}