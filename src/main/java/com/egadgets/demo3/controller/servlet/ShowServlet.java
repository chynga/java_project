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

@WebServlet(name = "ShowServlet", value = "/products/show")
public class ShowServlet extends HttpServlet {
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
        Product product = productService.getProductById(productId);
        User seller = userService.getUserById(product.getSellerId());
        if (userId != null && userId == seller.getId()) {
            res.sendRedirect("../myproducts/show?id=" + productId);
        } else {
            ArrayList<Message> chat = chatService.getChatById(productId, userId);

            req.setAttribute("product", product);
            req.setAttribute("seller", seller);
            req.setAttribute("chat", chat);

            req.getRequestDispatcher("/products/show.jsp").forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ChatService chatService = ChatService.getInstance();
        chatService.setDataSource(dataSource);

        Integer userId = (Integer) req.getSession().getAttribute("user_id");
        String reqProductId = req.getParameter("id");
//        Integer productId = Integer.parseInt(req.getParameter("id"));
//        productId = productId == null ? 0 : productId;
        if (reqProductId == null) {
            res.sendRedirect(req.getContextPath() + "/products");
        } else {
            int productId = Integer.parseInt(req.getParameter("id"));
            if (userId == null) {
                res.sendRedirect("../login");
            } else {
                String body = req.getParameter("body");
                Message message = new Message(body, Writer.CUSTOMER, userId, productId);
                chatService.insertMessage(message);
                doGet(req, res);
            }
        }
    }
}
