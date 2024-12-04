package com.example.services;

import com.example.entity.FileData;
import com.example.entity.ImageData;
import com.example.respository.FileDataRepository;
import com.example.respository.StorageRepository;
import com.example.demo.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository repository;

    private final String FOLDER_PATH="/Users/javatechie/Desktop/MyFIles/";

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}