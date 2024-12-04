package com.example.demo.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import com.example.demo.dto.Image;
import com.example.demo.services.ImageStorageService;
import com.example.demo.util.ImageUtils;

import org.springframework.web.multipart.MultipartFile;


public class ImageStorageImplementation implements ImageStorageService {

    @Override
    public byte[] uploadImage(MultipartFile file) throws IOException {
        return ImageUtils.compressImage(file.getBytes());
    }

    @Override
    public byte[] downloadImage(byte[] file) throws IOException{
        return ImageUtils.decompressImage(file);
    }
}