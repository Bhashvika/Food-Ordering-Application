package com.tap.servlets;

import com.tap.Dao.OrderDao;
import com.tap.Dao.OrderItemDao;
import com.tap.DaoImpl.OrderDaoImpl;
import com.tap.DaoImpl.OrderItemDaoImpl;
import com.tap.models.Order;
import com.tap.models.OrderItem;
import com.tap.models.CartModel;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/ConfirmOrderServlet")
public class ConfirmOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // 1. Get restaurantId from the POST request (most reliable)
        String restaurantIdParam = req.getParameter("restaurantId");
        
        Integer restaurantIdObj = null;

        if (restaurantIdParam != null && !restaurantIdParam.isEmpty()) {
            try {
            	System.out.println("Received restaurantIdParam: " + restaurantIdParam);

                restaurantIdObj = Integer.parseInt(restaurantIdParam);
            } catch (NumberFormatException e) {
                resp.getWriter().println("Invalid restaurant ID format.");
                return;
            }
        } else {
            restaurantIdObj = (Integer) session.getAttribute("restaurantId");
        }
        if (restaurantIdObj == null) {
            resp.getWriter().println("Session expired or restaurant not selected. Please go back and try again.");
            return;
        }

        int restaurantId = restaurantIdObj;

        // Get other session attributes
        List<CartModel> cart = (List<CartModel>) session.getAttribute("cartItems");
        Integer userIdObj = (Integer) session.getAttribute("userId");
        
        // Now check if any of the required values are missing
        if (cart == null || cart.isEmpty()) {
            resp.getWriter().println("Your cart is empty. Please add items before confirming the order.");
            return;
        }

        if (userIdObj == null || restaurantIdObj == null) {
            resp.getWriter().println("Session expired or invalid. Please login and try again.");
            return;
        }

        int userId = userIdObj;

 
        String address = req.getParameter("address");
        String paymentMode = req.getParameter("paymentMode");

        if (address == null || address.trim().isEmpty() || paymentMode == null || paymentMode.trim().isEmpty()) {
            resp.getWriter().println("Address and Payment Mode are required to place an order.");
            return;
        }

        double totalAmount = cart.stream().mapToDouble(CartModel::getTotalPrice).sum();

        OrderDao orderDao = new OrderDaoImpl();
        Order newOrder = new Order(restaurantId, userId, new Date(), totalAmount, "Confirmed", paymentMode);
        int orderId = orderDao.insertOrder(newOrder);

        if (orderId > 0) {
            OrderItemDao orderItemDao = new OrderItemDaoImpl();
            for (CartModel cartItem : cart) {
                OrderItem orderItem = new OrderItem(
                        0,
                        orderId,
                        cartItem.getMenuId(),
                        cartItem.getQuantity(),
                        cartItem.getTotalPrice()
                );
                orderItemDao.insertOrderItem(orderItem);
            }

            session.removeAttribute("cartItems");
            session.setAttribute("recentOrderId", orderId);

            resp.sendRedirect("orderconfirmation.html");
        } else {
            resp.getWriter().println("Failed to place the order. Please try again.");
        }
        System.out.println("ConfirmOrder Session ID: " + session.getId());
    }
}