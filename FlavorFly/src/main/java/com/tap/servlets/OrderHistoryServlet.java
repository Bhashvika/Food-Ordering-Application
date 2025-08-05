package com.tap.servlets;

import com.tap.Dao.OrderDao;
import com.tap.DaoImpl.OrderDaoImpl;
import com.tap.models.Order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer userIdObj = (Integer) session.getAttribute("userId");

        if (userIdObj == null) {
            resp.getWriter().println("Session expired. Please login again.");
            return;
        }

        int userId = userIdObj;
        System.out.println("Fetching order history for userId: " + userId);

        OrderDaoImpl orderDao = new OrderDaoImpl();
        List<Order> orderHistory = orderDao.getOrdersByUserId(userId);

        System.out.println("Order history size: " + orderHistory.size());  // Add this debug

        req.setAttribute("orderHistory", orderHistory);
        req.getRequestDispatcher("orderHistory.jsp").forward(req, resp);
    }
}
