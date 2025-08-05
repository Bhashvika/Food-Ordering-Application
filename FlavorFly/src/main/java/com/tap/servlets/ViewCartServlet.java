package com.tap.servlets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.tap.models.CartModel;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewCart")
public class ViewCartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        List<CartModel> cart = (List<CartModel>) session.getAttribute("cart");
        
        if (cart == null) {
            cart = new ArrayList<>();
        }

        resp.setContentType("application/json");
        resp.getWriter().write(new Gson().toJson(cart));
    }
}
