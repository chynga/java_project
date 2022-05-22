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

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    @Resource(name = "jdbc/project")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ProductService productService = ProductService.getInstance();
        productService.setDataSource(dataSource);
        String reqSelectedPage = req.getParameter("p");
        int selectedPage = Integer.parseInt(reqSelectedPage == null ? "1" : reqSelectedPage);

        int itemsForPage = 12;

        ArrayList<Product> products = productService.getProducts();
        int pages = products.size() / itemsForPage;
        if (products.size() % itemsForPage > 0) {
            pages++;
        }
        products = productService.getProductsForPage(products, itemsForPage, selectedPage);

        req.setAttribute("pages", pages);
        req.setAttribute("products", products);
        req.setAttribute("title", "Products");
        req.getRequestDispatcher("products/index.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
