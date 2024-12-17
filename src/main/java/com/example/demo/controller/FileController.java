package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.Web.UpdateUserDto;
import com.example.demo.model.Image;
import com.example.demo.services.ImageStorageService;
import com.example.demo.util.ImageUtils;


@RestController
@RequestMapping("/file")
public class FileController {
    
    @Autowired
    ImageStorageService imageService;

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        if(id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Image image = imageService.getImageBybId(id);


        return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.valueOf("image/png"))
        .body(ImageUtils.decompressImage(image.getData()));
    }
    
    @PostMapping
    public ResponseEntity<String> defaultImage(@ModelAttribute UpdateUserDto data) throws IOException{
        imageService.UploadImage(data.image());
        return new ResponseEntity<>("default uploaded", HttpStatus.OK);
    }
}
