package com.egadgets.demo3.dao;

import com.egadgets.demo3.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    public static void insertUser(DataSource dataSource, User user) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            String query = "INSERT INTO user (email, password, first_name, last_name, username, phone_number, city_id)" +
                           "VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5,user.getUsername());
            statement.setString(6,user.getPhoneNumber());
            statement.setInt(7, user.getCityId());
            statement.execute();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getUsers(DataSource dataSource) {
        ArrayList<User> users = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            String query = "Select * from user";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                users.add(new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("phone_number"), rs.getInt("city_id")));
            }
            connection.close();
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static User getUserByEmail(DataSource dataSource, String email) {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT * FROM user WHERE email = '" + email + "'";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("phone_number"), rs.getInt("city_id"));
            }

            connection.close();
            stmt.close();
            rs.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User getUserById(DataSource dataSource, int id) {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT * FROM user WHERE id = '" + id + "'";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("phone_number"), rs.getInt("city_id"));
            }

            connection.close();
            stmt.close();
            rs.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
