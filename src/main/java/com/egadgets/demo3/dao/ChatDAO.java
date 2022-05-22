package com.egadgets.demo3.dao;

import com.egadgets.demo3.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class ChatDAO {
    public static void insertMessage(DataSource dataSource, Message message) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            String query = "INSERT INTO chat (sentence, writer, time, customer_id, product_id)" +
                    "VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, message.getSentence());
            String writer = message.getWriter() == Writer.SELLER ? "s" : "c";
            statement.setString(2, writer);
            statement.setDate(3, new Date(System.currentTimeMillis()));
            statement.setInt(4, message.getCustomerId());
            statement.setInt(5, message.getProductId());
            statement.execute();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Message> getChatById(DataSource dataSource, int productId, int customerId) {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Message> chat = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            String query = "SELECT * FROM chat WHERE customer_id = '" + customerId + "' AND product_id = '" + productId + "'";

            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                int id = rs.getInt("id");
                String sentence = rs.getString("sentence");
                String writer = rs.getString("writer");
                Date date = rs.getDate("time");

                chat.add(new Message(id, sentence, writer, date, customerId, productId));
            }

            connection.close();
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chat;
    }

    public static ArrayList<Integer> getListOfCustomerId(DataSource dataSource, int productId) {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Integer> customerIds = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            String query = "SELECT DISTINCT customer_id FROM chat WHERE product_id = '" + productId + "'";

            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                customerIds.add(rs.getInt("customer_id"));
            }

            connection.close();
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerIds;
    }
}
