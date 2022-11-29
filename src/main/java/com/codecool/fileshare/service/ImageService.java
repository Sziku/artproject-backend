package com.codecool.fileshare.service;

import com.codecool.fileshare.dto.ImageDataDTO;
import com.codecool.fileshare.dto.ImageUpdateDTO;
import com.codecool.fileshare.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    @Qualifier("jdbc")
    private ImageRepository imageRepository;

    public List<ImageDataDTO> getAll(String user){ //Sziku
        //get owned by user.
        //help: imgurl = System.getenv("url") + "/api/artwork/" + id + "." +image.getExtension();
//TODO
    }

    public boolean checkOwner(String owner, String id){ //Adrian
//TODO

    }

    public void delete(String id, String owner) { //Adrian
//TODO

    }

    public void updateCategory(String id ,ImageUpdateDTO imageUpdateDTO, String owner) { //Balazs
//TODO
    }

    public byte[] getImageFile(String filename) {//Balazs //help: filename is for example 41d6608d-0803-4239-9235-09f902fbf705.jpg
//TODO
        // split("\\.")
    }

    public String storeFile(MultipartFile file, String title, String description, String owner) { //Sziku
        //help: filename is for example 41d6608d-0803-4239-9235-09f902fbf705.jpg
        //TODO
    }
}
