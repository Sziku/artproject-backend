package com.codecool.fileshare.repository;

import com.codecool.fileshare.model.Image;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("file")
public class ImageFileRepository implements ImageRepository{
    @Override
    public String storeImageFile(String title, String description, String owner, byte[] content, String extension) {
        //TODO
        return null;
    }

    @Override
    public boolean checkOwner(String owner, String id) {
        //TODO
        return false;
    }

    @Override
    public List<Image> getAll(String owner) {
        //TODO
        return null;
    }

    @Override
    public void delete(String uuid, String owner) {
        //TODO

    }

    @Override
    public void updateImage(String id, String title, String description, String owner) {
        //TODO

    }

    @Override
    public byte[] getImageFile(String id) {
        //TODO
        return new byte[0];
    }
    //Recomenden implementation. store data about users and images in csv files.
    //store the images in the same folder with image name like 41d6608d-0803-4239-9235-09f902fbf705.jpg

}
