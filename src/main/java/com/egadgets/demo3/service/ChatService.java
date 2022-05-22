package com.egadgets.demo3.service;

import com.egadgets.demo3.dao.ChatDAO;
import com.egadgets.demo3.dao.UserDAO;
import com.egadgets.demo3.model.Message;
import com.egadgets.demo3.model.User;

import javax.sql.DataSource;
import java.util.ArrayList;

public class ChatService {
    private DataSource dataSource;
    public static ChatService INSTANCE;

    private ChatService() {

    }

    public static ChatService getInstance() {
        if (INSTANCE == null) {
            synchronized (ChatService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ChatService();
                }
            }
        }
        return INSTANCE;
    }

    public ArrayList<Message> getChatById(int productId, int userId) {
        return ChatDAO.getChatById(dataSource, productId, userId);
    }

    public ArrayList<User> getCustomersWhoWrote(ArrayList<Integer> customerIds) {
        ArrayList<User> customersWhoWrote = new ArrayList<>();
        for (int id : customerIds) {
            User customer = UserDAO.getUserById(dataSource, id);
            customersWhoWrote.add(customer);
        }
        return customersWhoWrote;
    }

    public ArrayList<Integer> getListOfCustomerId(int productId) {
        return ChatDAO.getListOfCustomerId(dataSource, productId);
    }

    public void insertMessage(Message message) {
        ChatDAO.insertMessage(dataSource, message);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
