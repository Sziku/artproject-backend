package com.codecool.fileshare.repository;

import com.codecool.fileshare.exception.UserAlreadyExistsException;
import com.codecool.fileshare.model.AppUser;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserJdbcRepository implements UserRepository{

    static final String DB_TYPE = "jdbc:postgresql";

    static final String ADDRESS = System.getenv("host");

    static final int PORT = Integer.parseInt(System.getenv("port"));

    static final String DB_NAME = System.getenv("dbname");

    static final String DB_URL = DB_TYPE + "://" + ADDRESS + ":" + PORT + "/" + DB_NAME;

    static final String USER = System.getenv("dbusername");

    static final String PASS = System.getenv("dbpassword");
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
        final String SQL = "select email, password from app_user;";

        try(Connection con = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement st = con.prepareStatement(SQL);

            ResultSet rs = st.executeQuery();
            List<AppUser> appUserList = new ArrayList<>();
            while (rs.next()){
                appUserList.add(
                        new AppUser(
                                rs.getString(1),
                                rs.getString(2)
                        )
                );
            }
            return appUserList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
