package com.codecool.fileshare.repository;

import com.codecool.fileshare.model.Image;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component("jdbc")
public class ImageJdbcRepository implements ImageRepository{
    static final String DB_TYPE = "jdbc:postgresql";

    static final String ADDRESS = System.getenv("host");

    static final int PORT = Integer.parseInt(System.getenv("port"));

    static final String DB_NAME = System.getenv("dbname");

    static final String DB_URL = DB_TYPE + "://" + ADDRESS + ":" + PORT + "/" + DB_NAME;

    static final String USER = System.getenv("dbusername");

    static final String PASS = System.getenv("dbpassword");
    @Override  //Balazs
    public String storeImageFile(String title, String description, String owner, byte[] content, String extension) {
        //TODO
        return null;
    }

    @Override // Adrian
    public boolean checkOwner(String owner, String id) {
        //TODO

        //  where id = cast(? as uuid)
        return false;
    }

    @Override //Sziku
    public List<Image> getAll(String owner) {
        //TODO
        return null;
    }

    @Override // Adrian
    public void delete(String uuid, String owner) {
        //TODO

    }

    @Override //Balazs
    public void updateImage(String id, String title, String description, String owner) {
        //TODO

    }

    @Override //Sziku
    public byte[] getImageFile(String id) {
        //TODO
        return new byte[0];
    }
}
