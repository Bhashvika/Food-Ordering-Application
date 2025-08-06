<!-- Updated menu.jsp with Add to Cart button -->
<%@ page import="java.util.List" %>
<%@ page import="com.tap.models.Menu" %>
<%@ page import="com.tap.DaoImpl.MenuDaoImpl" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

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
                out.println("<p>Invalid restaurant ID format in session.</p>");
            }
        }
    }

    if (restaurantId == null) {
%>
        <p>No restaurant selected. Please go back and choose one.</p>
<%
    } else {
        MenuDaoImpl mdi = new MenuDaoImpl();
        List<Menu> menuList = mdi.getMenusByRestaurantId(restaurantId);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            padding: 20px;
            text-align: center;
        }
        h2 {
            color: #333;
        }
        .menu-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
        }
        .menu-card {
            background: white;
            border: 1px solid #ccc;
            border-radius: 10px;
            width: 300px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            text-align: left;
        }
        .menu-card img {
            width: 100%;
            height: 180px;
            object-fit: cover;
            border-radius: 8px;
            margin-bottom: 10px;
        }
        .menu-card h3 {
            color: #007bff;
            margin: 0;
        }
        .menu-card p {
            margin: 5px 0;
            color: #555;
        }
        .menu-card form {
            margin-top: 10px;
        }
        .menu-card input[type="number"] {
            width: 50px;
            padding: 5px;
        }
        .menu-card button {
            padding: 6px 10px;
            background-color: #28a745;
            border: none;
            color: white;
            border-radius: 4px;
            cursor: pointer;
        }
        .menu-card button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <h2>Menu Items</h2>
    <div class="menu-container">
        <% for (Menu m : menuList) { %>
            <div class="menu-card">
                <img src="<%= m.getImagePath() %>" alt="<%= m.getItemName() %> Image">
                <h3><%= m.getItemName() %></h3>
                <p><strong>Description:</strong> <%= m.getDescription() %></p>
                <p><strong>Price:</strong> ₹<%= m.getPrice() %></p>
                <p><strong>Available:</strong> <%= m.isAvailable() ? "Yes" : "No" %></p>
                <p><strong>Rating:</strong> <%= m.getRatings() %>/5</p>

                <form action="addToCart" method="post">
                    <input type="hidden" name="menuId" value="<%= m.getMenuId() %>" />
                    <input type="hidden" name="itemName" value="<%= m.getItemName() %>" />
                    <input type="hidden" name="price" value="<%= m.getPrice() %>" />
                    <label>Qty:</label>
                    <input type="number" name="quantity" value="1" min="1" required />
                    <button type="submit">Add to Cart</button>
                </form>
            </div>
        <% } %>
    </div>
    <br>
    <a href="cart.jsp"><button>View Cart</button></a>
</body>
</html>
<%
    }
%>
