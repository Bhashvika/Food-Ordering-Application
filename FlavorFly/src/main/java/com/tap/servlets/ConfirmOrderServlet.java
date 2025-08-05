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

        List<CartModel> cart = (List<CartModel>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            resp.getWriter().println("Your cart is empty. Please add items before confirming the order.");
            return;
        }

        Integer userIdObj = (Integer) session.getAttribute("userId");
        Integer restaurantIdObj = (Integer) session.getAttribute("restaurantId");

        if (userIdObj == null || restaurantIdObj == null) {
            resp.getWriter().println("Session expired or invalid. Please login and try again.");
            return;
        }

        int userId = userIdObj;
        int restaurantId = restaurantIdObj;

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

            session.removeAttribute("cart");
            session.setAttribute("recentOrderId", orderId);

            resp.sendRedirect("orderconfirmation.html");
        } else {
            resp.getWriter().println("Failed to place the order. Please try again.");
        }
        System.out.println("ConfirmOrder Session ID: " + session.getId());

    }
}
