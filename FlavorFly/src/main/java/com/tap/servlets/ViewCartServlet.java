package com.tap.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.tap.models.CartModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewCart")
public class ViewCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        List<CartModel> cart = (List<CartModel>) session.getAttribute("cartItems");
       
        if (cart == null) {
            cart = new ArrayList<>();
        }
        
        Gson gson = new Gson();
        String cartJson = gson.toJson(cart);

        PrintWriter out = response.getWriter();
        out.print(cartJson);
        out.flush();
       
    }
}
