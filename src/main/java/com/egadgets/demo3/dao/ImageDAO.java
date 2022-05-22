package com.egadgets.demo3.dao;

import com.egadgets.demo3.model.Image;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class ImageDAO {


    public static void insertImage(DataSource dataSource, Image image) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            String query = "INSERT INTO image_url (image_url, product_id)" +
                    "VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, image.getImageUrl());
            statement.setInt(2, image.getProductId());
            statement.execute();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Image> getImagesForProduct(DataSource dataSource, int productId) {
        ArrayList<Image> images = new ArrayList<>();
        Connection connect = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connect = dataSource.getConnection();
            String query = "Select * from image_url where product_id = " + productId;
            stmt = connect.prepareStatement(query);
//            stmt.setInt(1, productId);
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                images.add(new Image(rs.getInt("id"), rs.getString("image_url"), rs.getInt("product_id")));
            }
            connect.close();
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hello");
//            e.printStackTrace();
        }
        return images;
    }
}
