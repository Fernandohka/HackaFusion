package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.services.ImageStorageService;


@RestController
@RequestMapping("/teste")
public class example {
    
    @Autowired
    ImageStorageService service;


    @PostMapping
    public ResponseEntity<String> postMethodName(@RequestParam("image")MultipartFile file){
        
        // byte[] image = service.uploadImage(file);

        return new ResponseEntity<>("DEU BOA", HttpStatus.OK);
    }

    // @GetMapping
    // public ResponseEntity<byte[]> getMethodName() throws Exception {
    
    //     byte[] file = service.downloadImage("teste".getBytes());

    //     return new ResponseEntity<>(file, HttpStatus.OK);
    // }
    
    
}
