<%@ page import="java.util.List" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
     session = request.getSession(false);
    Integer restaurantId = null;
    if (session != null) {
        Object idObj = session.getAttribute("restaurantId");
        if (idObj instanceof Integer) {
            restaurantId = (Integer) idObj;
        } else if (idObj instanceof String) {
            try {
                restaurantId = Integer.parseInt((String) idObj);
            } catch (NumberFormatException e) {
                restaurantId = null;
            }
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
    <style>body {
            font-family: Arial, sans-serif;
            background: #f9f9f9;
            margin: 0;
            padding: 20px;
        }
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #4CAF50;
            height: 100px;
            padding: 0 20px;
        }
        .logo-container {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .logo-img {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            object-fit: cover;
            border: 2px solid #fff;
        }
        .brand-text {
            display: flex;
            flex-direction: column;
            line-height: 1.2;
            color: white;
        }
        .brand-text .title {
            font-size: 20px;
            font-weight: bold;
        }
        .brand-text .subtitle {
            font-size: 12px;
        }
        .nav-links {
            display: flex;
            gap: 20px;
        }
        .nav-links a {
            color: white;
            text-decoration: none;
            font-size: 18px;
            padding: 8px 12px;
            transition: background-color 0.3s;
        }
        .nav-links a:hover {
            background-color: #ddd;
            color: black;
            border-radius: 4px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .cart-container {
            max-width: 700px;
            margin: 0 auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .cart-item {
            border-bottom: 1px solid #eee;
            padding: 15px 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .item-info {
            flex: 1;
        }
        .item-info h4 {
            margin: 0;
            color: #333;
        }
        .item-info p {
            margin: 5px 0;
            color: #666;
        }
        .quantity-controls button {
            background: #007bff;
            border: none;
            color: white;
            padding: 5px 10px;
            margin: 0 3px;
            border-radius: 3px;
            cursor: pointer;
            font-size: 14px;
        }
        .quantity-controls button:hover {
            background: #0056b3;
        }
        .total {
            font-weight: bold;
            font-size: 1.2em;
            text-align: right;
            margin-top: 20px;
            color: #333;
        }
        .checkout-btn {
            display: block;
            width: 100%;
            background: #28a745;
            color: white;
            border: none;
            padding: 12px;
            font-size: 16px;
            border-radius: 5px;
            margin-top: 20px;
            cursor: pointer;
        }
        .checkout-btn:hover {
            background: #218838;
        }
    </style>
</head>
<body>
<div class="navbar">
    <div class="logo-container">
        <img src="logo.png" alt="Flavour Fly Logo" class="logo-img" />
        <div class="brand-text">
            <span class="title">Flavour Fly</span>
            <span class="subtitle">Taste Of Andhra</span>
        </div>
    </div>
    <div class="nav-links">
        <a href="UserDashboard.jsp">Home</a>
        <a href="restaurant.jsp">Restaurants</a>
        <a href="cart.jsp">Cart</a>
        <a href="orderHistory.jsp">Order History</a>
        <a href="signin.jsp">Logout</a>
    </div>
</div>
<h1>Your Cart</h1>
<div class="cart-container">
    <div id="cart-list"></div>
    <div class="total" id="cart-total"></div>
    <a id="checkout-link" href="#">Proceed to Checkout</a>
</div>
<script>
    let cart = [];

    async function fetchCart() {
        try {
            const response = await fetch('/FlavorFly/viewCart');
            if (!response.ok) throw new Error("Failed to fetch cart");

            cart = await response.json();
            renderCart();
        } catch (err) {
            console.error("Cart fetch error:", err);
            document.getElementById('cart-list').innerHTML = '<p>Error loading cart.</p>';
        }
    }

    function renderCart() {
        const container = document.getElementById('cart-list');
        const totalContainer = document.getElementById('cart-total');

        if (!cart || cart.length === 0) {
            container.innerHTML = '<p>Your cart is empty.</p>';
            totalContainer.textContent = '';
            return;
        }

        let total = 0;
        container.innerHTML = '';

        cart.forEach((item, index) => {
            const itemTotal = item.price * item.quantity;
            total += itemTotal;

            container.innerHTML += `
                <div class="cart-item">
                    <div class="item-info">
                        <h4>${item.itemName}</h4>
                        <p>Price: ₹${item.price}</p>
                        <p>Subtotal: ₹${itemTotal.toFixed(2)}</p>
                    </div>
                    <div class="quantity-controls">
                        <button onclick="updateQuantity(${index}, 1)">+</button>
                        <span>${item.quantity}</span>
                        <button onclick="updateQuantity(${index}, -1)">-</button>
                    </div>
                </div>
            `;
        });

        totalContainer.textContent = `Total: ₹${total.toFixed(2)}`;
    }

    function updateQuantity(index, delta) {
        cart[index].quantity += delta;
        if (cart[index].quantity <= 0) {
            cart.splice(index, 1);
        }

        fetch('/FlavorFly/updateCart', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(cart)
        }).then(response => response.text())
          .then(data => {
              console.log(data);
              renderCart();
          })
          .catch(err => console.error("Failed to update cart", err));
    }

    document.getElementById('checkout-link').onclick = function(e) {
        <% if (restaurantId != null) { %>
            e.preventDefault();
            window.location.href = 'checkout.jsp?restaurantId=<%= restaurantId %>';
        <% } %>
    };

    fetchCart();
</script>
</body>
</html>
