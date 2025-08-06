package com.tap.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/selectRestaurant")
public class SelectRestaurantServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("restaurantId");

        if (idStr != null && !idStr.isEmpty()) {
            int restaurantId = Integer.parseInt(idStr);
            HttpSession session = req.getSession();
            session.setAttribute("restaurantId", restaurantId);

            // Debug: Confirm it's set
            System.out.println("Selected restaurant ID stored in session: " + restaurantId);
            
            req.getRequestDispatcher("Menu.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid restaurant ID");
        }
    }
}
