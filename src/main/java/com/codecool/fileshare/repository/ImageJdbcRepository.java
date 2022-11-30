package com.codecool.fileshare.repository;

import com.codecool.fileshare.model.Image;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component("jdbc")
public class ImageJdbcRepository implements ImageRepository{
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/" + System.getenv("dbname");
    private static final String USER = System.getenv("dbusername");
    private static final String PASS = System.getenv("dbpassword");

    @Override  //Balazs
    public String storeImageFile(String title, String description, String owner, byte[] content, String extension) {
        final String sql = "INSERT INTO image(title,owner,description,content,extension) VALUES(?,?,?,?,?);";
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,title);
            statement.setString(2,owner);
            statement.setString(3,description);
            statement.setBytes(4,content);
            statement.setString(5,extension);

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getString(1);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override // Adrian
    public boolean checkOwner(String owner, String id) {
        //  where id = cast(? as uuid)
        final String sql = "SELECT owner FROM image WHERE id = CAST(? as uuid);";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getString(1).equals(owner);

        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    @Override //Sziku
    public List<Image> getAll(String owner) {
        List<Image> results = new ArrayList<>();

        final String sql = "SELECT id,title,owner,description,content,extension FROM image WHERE owner = ?;";
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,owner);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Image image = new Image(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBytes(5),
                        resultSet.getString(6));
                results.add(image);
            }

            return results;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override // Adrian
    public void delete(String uuid, String owner) {
        final String sql = "DELETE FROM image WHERE id = CAST(? as uuid) AND owner = ?;";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,uuid);
            statement.setString(2,owner);

            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override //Balazs
    public void updateImage(String id, String title, String description, String owner) {
        final String sql = "UPDATE image SET title = ?, description = ?, owner = ? WHERE id = CAST(? as uuid);";
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,title);
            statement.setString(2,description);
            statement.setString(3,owner);
            statement.setString(4,id);

            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override //Sziku
    public byte[] getImageFile(String id) {
        final String sql = "SELECT content FROM image WHERE id = CAST(? as uuid);";
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getBytes(1);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
