package com.egadgets.demo3.controller.servlet;

import com.egadgets.demo3.model.Message;
import com.egadgets.demo3.model.Product;
import com.egadgets.demo3.model.User;
import com.egadgets.demo3.model.Writer;
import com.egadgets.demo3.service.ChatService;
import com.egadgets.demo3.service.ProductService;
import com.egadgets.demo3.service.UserService;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MyProductShowServlet", value = "/myproducts/show")
public class MyProductShowServlet extends HttpServlet {
    @Resource(name = "jdbc/project")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ProductService productService = ProductService.getInstance();
        productService.setDataSource(dataSource);
        UserService userService = UserService.getInstance();
        userService.setDataSource(dataSource);
        ChatService chatService = ChatService.getInstance();
        chatService.setDataSource(dataSource);

        int productId = Integer.parseInt(req.getParameter("id"));
        Integer userId = (Integer) req.getSession().getAttribute("user_id");
        Integer customerId = Integer.parseInt(req.getParameter("customer_id") != null ? req.getParameter("customer_id") : "0");
        Product product = productService.getProductById(productId);
        User seller = userService.getUserById(product.getSellerId());
        ArrayList<Integer> customerIds = chatService.getListOfCustomerId(productId);
        ArrayList<User> customersWhoWrote = chatService.getCustomersWhoWrote(customerIds);

        if (userId == seller.getId()) {
            ArrayList<Message> chat = chatService.getChatById(productId, customerId);
            req.setAttribute("product", product);
            req.setAttribute("seller", seller);
            req.setAttribute("chat", chat);
            req.setAttribute("customers", customersWhoWrote);
            req.setAttribute("customer_id", customerId);

            req.getRequestDispatcher("/myproducts/show.jsp").forward(req, res);
        } else {
            res.sendRedirect("products");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "delete":
                deleteProduct(req, res);
                break;
            case "write":
                sendMessage(req, res);
                break;
            default:
                res.sendRedirect("show");
                break;
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ProductService productService = ProductService.getInstance();
        productService.setDataSource(dataSource);

        int productId = Integer.parseInt(req.getParameter("id"));
        productService.deleteProductById(productId);
        res.sendRedirect("../products");
    }

    private void sendMessage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ChatService chatService = ChatService.getInstance();
        chatService.setDataSource(dataSource);

        int productId = Integer.parseInt(req.getParameter("id"));
        Integer customerId = Integer.parseInt(req.getParameter("customer_id") != null ? req.getParameter("customer_id") : "0");

        String body = req.getParameter("body");
        Message message = new Message(body, Writer.SELLER, customerId, productId);
        chatService.insertMessage(message);
        doGet(req, res);
    }
}
