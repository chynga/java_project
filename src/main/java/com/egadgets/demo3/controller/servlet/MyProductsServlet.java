package com.egadgets.demo3.controller.servlet;

import com.egadgets.demo3.model.Product;
import com.egadgets.demo3.service.ProductService;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MyProductsServlet", value = "/myproducts")
public class MyProductsServlet extends HttpServlet {
    @Resource(name = "jdbc/project")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ProductService productService = ProductService.getInstance();
        productService.setDataSource(dataSource);

        Integer userId = (Integer) req.getSession().getAttribute("user_id");
        if (userId != null) {
            ArrayList<Product> products = productService.getProductsBySellerId(userId);
            req.setAttribute("products", products);
            req.setAttribute("title", "My Products");
            req.getRequestDispatcher("myproducts/index.jsp").forward(req, res);
        } else {
            res.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
