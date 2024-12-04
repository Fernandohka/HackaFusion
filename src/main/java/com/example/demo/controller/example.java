package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.Image;
import com.example.demo.model.Ability;
import com.example.demo.repository.AbilityRepository;
import com.example.demo.services.ImageStorageService;


@RestController
@RequestMapping("/teste")
public class example {
    
    @Autowired
    ImageStorageService service;

    @Autowired
    AbilityRepository repo;


    @PostMapping
    public ResponseEntity<String> postMethodName(@RequestParam("image")MultipartFile file) throws IOException{
        
        Image image = service.uploadImage(file);

        Ability ability = new Ability();
        ability.setName("name");
        ability.setImageData(image.getData());
        ability.setImageName(image.getName());

        repo.save(ability);

        return new ResponseEntity<>("DEU BOA", HttpStatus.OK);
    }
    
}
