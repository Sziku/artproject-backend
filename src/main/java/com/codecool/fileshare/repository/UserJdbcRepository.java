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
        final String sql = "SELECT email, password FROM app_user WHERE email = ?;";

        try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS)){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            AppUser appUser = new AppUser();
            appUser.setEmail(resultSet.getString(1));
            appUser.setPassword(resultSet.getString(2));

            return appUser;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override //Roland
    public void save(AppUser appUser) throws UserAlreadyExistsException {
        final String sql = "INSERT INTO app_user(email,password) VALUES(?,?);";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,appUser.getEmail());
            statement.setString(2,appUser.getPassword());

            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override //Sziku
    public List<AppUser> getAppUsers() {
        final String sql = "SELECT email, password FROM app_user;";
        List<AppUser> results = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS)){
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                AppUser appUser = new AppUser();
                appUser.setEmail(resultSet.getString(1));
                appUser.setPassword(resultSet.getString(2));

                results.add(appUser);
            }
            return results;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
