package com.codecool.fileshare.repository;

import com.codecool.fileshare.exception.UserAlreadyExistsException;
import com.codecool.fileshare.model.AppUser;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserJdbcRepository implements UserRepository{


    @Override //Roland
    public AppUser findByUsername(String username) {
        //TODO
        return null;
    }

    @Override //Roland
    public void save(AppUser appUser) throws UserAlreadyExistsException {
//TODO
    }

    @Override //Sziku
    public List<AppUser> getAppUsers() {
        //TODO
        return null;
    }
}
