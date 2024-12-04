package com.example.demo.services.impl;

import java.io.IOException;



import com.example.demo.dto.Image;
import com.example.demo.services.ImageStorageService;
import com.example.demo.util.ImageUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public class ImageStorageImplementation implements ImageStorageService {

    @Override
    public Image uploadImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setData(ImageUtils.compressImage(file.getBytes()));
        return image;
    }

    @Override
    public byte[] downloadImage(Image image) {
        byte[] images = ImageUtils.decompressImage(image.getData());
        return images;
    }
}