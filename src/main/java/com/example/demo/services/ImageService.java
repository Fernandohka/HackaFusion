package com.example.demo.services;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface ImageService {

    // Save image in a local directory
    public String saveImageToStorage(String uploadDirectory, MultipartFile imageFile);

    // Delete an image
    public String deleteImage(String imageDirectory, String imageName);
}