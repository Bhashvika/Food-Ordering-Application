package com.tap.servlets;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.tap.DaoImpl.MenuDaoImpl;
import com.tap.models.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/api/menu")
public class MenuServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String restaurantIdParam = req.getParameter("restaurantId");
        
        if (restaurantIdParam == null || restaurantIdParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameter: restaurantId");
            return;
        }

        int restaurantId = Integer.parseInt(restaurantIdParam);
        HttpSession session = req.getSession();
        
        // This line correctly stores the ID in the session
        session.setAttribute("restaurantId", restaurantId);

        // --- DEBUGGING: Print to the console to confirm the value is set ---
        System.out.println("MenuServlet: Stored restaurantId " + restaurantId + " in session with ID " + session.getId());
        
        MenuDaoImpl mdi = new MenuDaoImpl();
        List<Menu> items = mdi.getMenusByRestaurantId(restaurantId);

        resp.setContentType("application/json");
        new Gson().toJson(items, resp.getWriter());
    }
}