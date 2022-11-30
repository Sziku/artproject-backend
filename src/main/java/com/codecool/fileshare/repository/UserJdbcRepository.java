package com.codecool.fileshare.repository;

import com.codecool.fileshare.exception.UserAlreadyExistsException;
import com.codecool.fileshare.model.AppUser;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserJdbcRepository implements UserRepository{

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/" + System.getenv("dbname");
    private static final String USER = System.getenv("dbusername");
    private static final String PASS = System.getenv("dbpassword");


    @Override //Roland
    public AppUser findByUsername(String username) {

    }

    @Override //Roland
    public void save(AppUser appUser) throws UserAlreadyExistsException {

    }

    @Override //Sziku
    public List<AppUser> getAppUsers() {

    }
}
