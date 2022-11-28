package com.codecool.fileshare.repository;

import com.codecool.fileshare.model.Image;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component("jdbc")
public class ImageJdbcRepository implements ImageRepository{

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
}
