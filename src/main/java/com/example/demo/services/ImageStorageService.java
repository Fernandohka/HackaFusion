package com.example.demo.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Image;


public interface ImageStorageService {
    public Long UploadImage(MultipartFile file) throws IOException;
    public Long UploadImage(byte[] file) throws IOException;
    public ResponseDto UpdateImage(Long idImage,MultipartFile file) throws IOException;
    public byte[] descompressImage(byte[] file);
    public String toUrl(Long idImage);
    public Image getImageBybId(Long idImage);
}