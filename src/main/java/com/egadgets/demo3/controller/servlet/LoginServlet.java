package com.egadgets.demo3.controller.servlet;

import com.egadgets.demo3.model.User;
import com.egadgets.demo3.service.UserService;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Resource(name = "jdbc/project")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("users/login.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserService userService = UserService.getInstance();
        userService.setDataSource(dataSource);
        User user = userService.login(email, password);

        if (user != null) {
            req.getSession().invalidate();
            HttpSession newSession = req.getSession(true);
            newSession.setAttribute("user_id", user.getId());
            res.sendRedirect("products");
        } else {
            res.sendRedirect("login");
        }
    }
}
