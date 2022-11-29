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

    @Override
    public String storeImageFile(String title, String description, String owner, byte[] content, String extension) {
        //TODO
        final String SQL = "insert into image(title, owner, description, content, extension) values(?,?,?,?,?);";
        String id  = null;
        try(Connection con = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement st = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                st.setString(1, title);
                st.setString(2, owner);
                st.setString(3, description);
                st.setBytes(4, content);
                st.setString(5, extension);

            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            id  = rs.getString(1);
            return id;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkOwner(String owner, String id) {
        //TODO
        final String SQL = "select owner from image where id = cast(? as uuid);";

        try(Connection con = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, id);

            ResultSet rs = st.executeQuery();
            rs.next();
            return rs.getString(1).equals(owner);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Image> getAll(String owner) {
        //TODO
        final String SQL = "select id, title, owner, description, content, extension from image where owner = ?;";

        try(Connection con = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, owner);

           ResultSet rs = st.executeQuery();
            List<Image> imageList = new ArrayList<>();
            while (rs.next()){
                imageList.add(new Image(rs.getString(1),
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getString(4),
                                        rs.getBytes(5),
                                        rs.getString(6)));
            }

            return imageList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(String uuid, String owner) {
        //TODO
        final String SQL = "delete from image where id = cast(? as uuid) and owner = ?;";

        try(Connection con = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, uuid);
            st.setString(2, owner);

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateImage(String id, String title, String description, String owner) {
        //TODO
        final String SQL = "update image set title = ?, owner = ?, description = ? where id = cast(? as uuid) ;";

        try(Connection con = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement st = con.prepareStatement(SQL);
                st.setString(1, title);
                st.setString(2, owner);
                st.setString(3, description);
                st.setString(4, id);

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] getImageFile(String id) {
        //TODO
        final String SQL = "select content from image where id = cast(? as uuid) ;";

        try(Connection con = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, id);

            ResultSet rs = st.executeQuery();
            rs.next();

            return rs.getBytes(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
