package com.egadgets.demo3.controller.servlet;

import com.egadgets.demo3.model.City;
import com.egadgets.demo3.model.User;
import com.egadgets.demo3.service.CityService;
import com.egadgets.demo3.service.UserService;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Resource(name = "jdbc/project")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        CityService cityService = CityService.getInstance();
        cityService.setDataSource(dataSource);

        ArrayList<City> cities = cityService.getCities();
        req.setAttribute("cities", cities);

        req.getRequestDispatcher("users/register.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserService userService = UserService.getInstance();
        userService.setDataSource(dataSource);

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String phone = req.getParameter("phone");
        int cityId = Integer.parseInt(req.getParameter("city_id"));

        User user = new User(email, password, firstname, lastname, username, phone, cityId);

        int userId = userService.register(user);

        req.getSession().invalidate();
        HttpSession newSession = req.getSession(true);
        newSession.setAttribute("user_id", userId);
        res.sendRedirect("products");
    }
}
