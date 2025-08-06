package com.tap.servlets;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.tap.DaoImpl.RestaurantDaoImpl;
import com.tap.models.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/api/restaurants")
public class RestaurantServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestaurantDaoImpl rdi = new RestaurantDaoImpl();
        List<Restaurant> restaurants = rdi.getAllRestaurants();
        req.setAttribute("restaurants", restaurants);
        RequestDispatcher rd = req.getRequestDispatcher("restaurant.jsp");
        rd.forward(req, resp);
    }
}

