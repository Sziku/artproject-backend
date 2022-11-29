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

    public List<ImageDataDTO> getAll(String user){
        //get owned by user.
        //help: imgurl = System.getenv("url") + "/api/artwork/" + id + "." +image.getExtension();
        //TODO
        List<ImageDataDTO> imageDataDTOList = new ArrayList<>();
        for(Image image : imageRepository.getAll(user)){
            String imgurl = System.getenv("url") + "/api/artwork/" + image.getId() + "." +image.getExtension();

            imageDataDTOList.add(new ImageDataDTO(
                    image.getId(),
                    image.getTitle(),
                    image.getDescription(),
                    imgurl
            ));
        }
        return imageDataDTOList;
    }

    public boolean checkOwner(String owner, String id){
//TODO
        return imageRepository.checkOwner(owner,id);
    }

    public void delete(String id, String owner) {
//TODO
        imageRepository.delete(id,owner);
    }

    public void updateCategory(String id ,ImageUpdateDTO imageUpdateDTO, String owner) {
//TODO
        imageRepository.updateImage(id,imageUpdateDTO.getTitle(),imageUpdateDTO.getDescription(),owner);
    }

    public byte[] getImageFile(String filename) { //help: filename is for example 41d6608d-0803-4239-9235-09f902fbf705.jpg
//TODO
        String[] fileNameSplit = filename.split("\\.");
        return imageRepository.getImageFile(fileNameSplit[0]);
    }

    public String storeFile(MultipartFile file, String title, String description, String owner) {
        //help: filename is for example 41d6608d-0803-4239-9235-09f902fbf705.jpg
        //TODO
        String[] fileNameSplit = file.getOriginalFilename().split("\\.");
        try {
            return imageRepository.storeImageFile(title,description,owner,file.getBytes(),fileNameSplit[1]);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
