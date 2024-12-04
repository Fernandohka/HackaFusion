package com.example.demo.controller;

import java.awt.PageAttributes;
import java.awt.image.BufferedImage;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.Image;
import com.example.demo.model.Ability;
import com.example.demo.repository.AbilityRepository;
import com.example.demo.services.ImageStorageService;

import org.springframework.web.bind.annotation.GetMapping;

import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

@RestController
@RequestMapping("/teste")
public class example {
    
    @Autowired
    ImageStorageService service;


    @PostMapping
    public ResponseEntity<String> postMethodName(@RequestParam("image")MultipartFile file) throws IOException{
        
        byte[] image = service.uploadImage(file);

        return new ResponseEntity<>("DEU BOA", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<File> getMethodName() throws Exception {
    
        // File file = service.downloadImage(ability.getImageData());

        return new ResponseEntity<>(file, HttpStatus.OK);
    }
    
    
}
