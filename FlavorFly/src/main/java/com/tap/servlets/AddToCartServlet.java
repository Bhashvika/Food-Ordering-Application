package com.tap.servlets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.tap.models.CartModel;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        int menuId = Integer.parseInt(req.getParameter("menuId"));
        String itemName = req.getParameter("itemName");
        double price = Double.parseDouble(req.getParameter("price"));

        List<CartModel> cart = (List<CartModel>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean found = false;
        for (CartModel item : cart) {
            if (item.getMenuId() == menuId) {
                item.setQuantity(item.getQuantity() + 1);
                found = true;
                break;
            }
        }

        if (!found) {
            cart.add(new CartModel(menuId, itemName, 1, price));
        }

        session.setAttribute("cart", cart);
        resp.getWriter().write("Item added to cart");
        System.out.println("AddToCart Session ID: " + session.getId());

    }
}
