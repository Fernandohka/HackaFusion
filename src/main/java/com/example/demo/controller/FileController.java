package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Image;
import com.example.demo.services.ImageStorageService;


@RestController
@RequestMapping("/file")
public class FileController {
    
    @Autowired
    ImageStorageService imageService;

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@RequestParam Long id) {
        if(id == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        Image image = imageService.getImageBybId(id);

        return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.valueOf("image/png"))
        .body(image.getImage());
        
    }
    
}
