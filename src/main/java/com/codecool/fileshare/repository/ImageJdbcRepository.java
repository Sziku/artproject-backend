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
        //TODO

    }

    @Override //Sziku
    public byte[] getImageFile(String id) {
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
