package com.example.demo.services.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Image;
import com.example.demo.repositories.ImageRepo;
import com.example.demo.services.ImageStorageService;
import com.example.demo.util.ImageUtils;


public class ImageStorageImplementation implements ImageStorageService {

    @Autowired
    ImageRepo repository;



    @Override
    public Long UploadImage(MultipartFile file) throws IOException {
        if(file == null){
            System.out.println("Image vazia!");
            return null;
        }
        Image image = new Image();
        image.setData(ImageUtils.compressImage(file.getBytes()));
        
        repository.save(image);

        return image.getId();
    }

    @Override
    public ResponseDto UpdateImage(Long idImage, MultipartFile file) throws IOException {
        if(idImage == null || file == null)
            return new ResponseDto(false, "Parâmetros inváldos");

        Optional<Image> image_opt = repository.findById(idImage);
        if(!image_opt.isPresent())
            return new ResponseDto(false, "Id inválido!");
        
        Image image = image_opt.get();
        image.setData(ImageUtils.compressImage(file.getBytes()));
        repository.save(image);

        return new ResponseDto(true, "Imagem atualizada com sucesso!");
    }

    @Override
    public byte[] descompressImage(byte[] file) {
        return ImageUtils.decompressImage(file);
    }

    @Override
    public String toUrl(Long idImage) {
        return "http://localhost:8080/file/image/" + idImage;
    }

    @Override
    public Image getImageBybId(Long idImage) {
        if(idImage == null)
            return null;
        Optional<Image> opt_image = repository.findById(idImage);
        if(!opt_image.isPresent())
            return null;

        return opt_image.get(); 
    }

    @Override
    public Long UploadImage(byte[] file) throws IOException {
        if(file == null){
            System.out.println("Image vazia!");
            return null;
        }
        Image image = new Image();
        image.setData(file);
        
        repository.save(image);

        return image.getId();
    }

    
}