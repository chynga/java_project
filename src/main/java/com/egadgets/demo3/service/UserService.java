package com.egadgets.demo3.service;

import com.egadgets.demo3.dao.UserDAO;
import com.egadgets.demo3.model.User;

import javax.sql.DataSource;
import java.util.ArrayList;

public class UserService {
    private DataSource dataSource;
    public static UserService INSTANCE;

    private UserService() {

    }

    public static UserService getInstance() {
        if (INSTANCE == null) {
            synchronized (UserService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserService();
                }
            }
        }
        return INSTANCE;
    }

    public User login(String email, String password) {
        ArrayList<User> users = UserDAO.getUsers(dataSource);
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public int register(User user) {
        UserDAO.insertUser(dataSource, user);
        User insertedUser = UserDAO.getUserByEmail(dataSource, user.getEmail());
        return insertedUser.getId();
    }

    public User getUserById(int id) {
        return UserDAO.getUserById(dataSource, id);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
