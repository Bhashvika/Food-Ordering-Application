package com.tap.servlets;

import com.tap.models.CartModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Validate and parse input parameters
        String menuIdParam = req.getParameter("menuId");
        String itemName = req.getParameter("itemName");
        String quantityParam = req.getParameter("quantity");
        String priceParam = req.getParameter("price");

        if (menuIdParam == null || itemName == null || quantityParam == null || priceParam == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters.");
            return;
        }

        int menuId;
        int quantity;
        double price;

        try {
            menuId = Integer.parseInt(menuIdParam);
            quantity = Integer.parseInt(quantityParam);
            price = Double.parseDouble(priceParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format.");
            return;
        }

        // Create a CartModel object with provided data
        CartModel item = new CartModel(menuId, itemName, quantity, price);

        // Get or create the cart stored in session
        HttpSession session = req.getSession(true);
        List<CartModel> cart = (List<CartModel>) session.getAttribute("cartItems");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // Add new item to the cart
        cart.add(item);
        session.setAttribute("cartItems", cart);

        // Redirect to cart page or confirmation page
        resp.sendRedirect(req.getContextPath() + "/cart.html");
    }
}
