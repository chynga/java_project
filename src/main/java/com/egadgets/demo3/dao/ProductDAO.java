package com.egadgets.demo3.dao;

import com.egadgets.demo3.model.Image;
import com.egadgets.demo3.model.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {
    public static ArrayList<Product> getProducts(DataSource dataSource) {
        ArrayList<Product> products = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            String query = "Select * from product";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                int id = rs.getInt("id");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                int sellerId = rs.getInt("seller_id");
                ArrayList<Image> images = null;
                try {
                    images = ImageDAO.getImagesForProduct(dataSource, id);
                } catch (Exception e) {
                    images = new ArrayList<>();
                }
                products.add(new Product(id, productName, description, price, sellerId, images));
            }
            connection.close();
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public static Product getProductById(DataSource dataSource, int id) {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        Product product = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT * FROM product WHERE id = '" + id + "'";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                int sellerId = rs.getInt("seller_id");
                ArrayList<Image> images = null;
                try {
                    images = ImageDAO.getImagesForProduct(dataSource, id);
                } catch (Exception e) {
                    images = new ArrayList<>();
                }
                product = new Product(id, productName, description, price, sellerId, images);
            }

            connection.close();
            stmt.close();
            rs.close();
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static ArrayList<Product> getProductsBySellerId(DataSource dataSource, int sellerId) {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Product> products = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            String query = "SELECT * FROM product WHERE seller_id = '" + sellerId + "'";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                int id = rs.getInt("id");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                ArrayList<Image> images = null;
                try {
                    images = ImageDAO.getImagesForProduct(dataSource, id);
                } catch (Exception e) {
                    images = new ArrayList<>();
                }
                products.add(new Product(id, productName, description, price, sellerId, images));
            }

            connection.close();
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static void insertProduct(DataSource dataSource, Product product) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            String query = "INSERT INTO vwproduct (product_name, description, price, seller_id)" +
                    "VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getPrice());
            statement.setInt(4, product.getSellerId());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int productId = generatedKeys.getInt(1);
                    for (Image image : product.getImages()) {
                        image.setProductId(productId);
                        ImageDAO.insertImage(dataSource, image);
                    }
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteProductById(DataSource dataSource, int id) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = dataSource.getConnection();
            String query = "CALL sp_deleteProduct(?);";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
//            while(rs.next()) {
//                int id = rs.getInt("id");
//                String productName = rs.getString("product_name");
//                String description = rs.getString("description");
//                float price = rs.getFloat("price");
//                ArrayList<Image> images = null;
//                try {
//                    images = ImageDAO.getImagesForProduct(dataSource, id);
//                } catch (Exception e) {
//                    images = new ArrayList<>();
//                }
//                products.add(new Product(id, productName, description, price, sellerId, images));
//            }

            connection.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
