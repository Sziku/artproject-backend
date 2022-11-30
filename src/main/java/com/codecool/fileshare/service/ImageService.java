package com.codecool.fileshare.service;

import com.codecool.fileshare.dto.ImageDataDTO;
import com.codecool.fileshare.dto.ImageUpdateDTO;
import com.codecool.fileshare.model.Image;
import com.codecool.fileshare.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    @Qualifier("jdbc")
    private ImageRepository imageRepository;

    public List<ImageDataDTO> getAll(String user){ //Sziku
        //get owned by user.
        //help: imgurl = System.getenv("url") + "/api/artwork/" + id + "." +image.getExtension();
        List<ImageDataDTO> results = new ArrayList<>();
        List<Image> images = imageRepository.getAll(user);
        for (Image image : images) {
            ImageDataDTO imageDataDTO = new ImageDataDTO();
            imageDataDTO.setId(image.getId());
            imageDataDTO.setTitle(image.getTitle());
            imageDataDTO.setDescription(image.getDescription());
            imageDataDTO.setUrl(System.getenv("url") + "/api/artwork/" + image.getId() + "." + image.getExtension());

            results.add(imageDataDTO);
        }
        return results;

    }

    public boolean checkOwner(String owner, String id){ //Adrian
        return imageRepository.checkOwner(owner, id);
    }

    public void delete(String id, String owner) { //Adrian
        imageRepository.delete(id, owner);
    }

    public void updateCategory(String id ,ImageUpdateDTO imageUpdateDTO, String owner) { //Balazs
        imageRepository.updateImage(id,imageUpdateDTO.getTitle(), imageUpdateDTO.getDescription(), owner);
    }

    public byte[] getImageFile(String filename) {//Balazs //help: filename is for example 41d6608d-0803-4239-9235-09f902fbf705.jpg
        String id = filename.split("\\.")[0];
        return imageRepository.getImageFile(id);
    }

    public String storeFile(MultipartFile file, String title, String description, String owner) { //Sziku
        //help: filename is for example 41d6608d-0803-4239-9235-09f902fbf705.jpg
        String[] fileNameSplit = file.getOriginalFilename().split("\\.");
        try {
            return imageRepository.storeImageFile(title,description,owner,file.getBytes(),fileNameSplit[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
