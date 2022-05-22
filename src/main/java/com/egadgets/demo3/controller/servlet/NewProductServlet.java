package com.egadgets.demo3.controller.servlet;

import com.egadgets.demo3.model.Image;
import com.egadgets.demo3.model.Product;
import com.egadgets.demo3.service.ProductService;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "NewProductServlet", value = "/myproducts/new")
public class NewProductServlet extends HttpServlet {
    @Resource(name = "jdbc/project")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("new.jsp").forward(req, res);
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String price = req.getParameter("price");
        String image = req.getParameter("image");

        if (name != null && description != null && price != null && image != null) {
            doPost(req, res);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ProductService productService = ProductService.getInstance();
        productService.setDataSource(dataSource);

        Integer userId = (Integer) req.getSession().getAttribute("user_id");

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        float price = Float.parseFloat(req.getParameter("price"));
        String imageURL = req.getParameter("image");
        ArrayList<Image> images = new ArrayList<>();
        images.add(new Image(imageURL));

        Product product = new Product(name, description, price, userId, images);
        productService.insertProduct(product);

    }
}
