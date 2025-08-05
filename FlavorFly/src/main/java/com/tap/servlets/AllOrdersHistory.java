package com.tap.servlets;

import java.io.IOException;
import java.util.List;

import com.tap.DaoImpl.OrderDaoImpl;
import com.tap.models.Order;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/allOrders")
public class AllOrdersHistory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDaoImpl od = new OrderDaoImpl();
        List<Order> allorders = od.getAllOrders();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        new com.google.gson.Gson().toJson(allorders, resp.getWriter());
    }
}

