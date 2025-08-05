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
        int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
        HttpSession session = req.getSession();
        session.setAttribute("restaurantId", restaurantId);
        MenuDaoImpl mdi=new MenuDaoImpl();
        List<Menu> items = mdi.getMenusByRestaurantId(restaurantId);
        
        resp.setContentType("application/json");
        new Gson().toJson(items, resp.getWriter());
    }
}
