<!-- restaurant.jsp -->
<%@ page import="java.util.List" %>
<%@ page import="com.tap.models.Restaurant" %>
<%@ page import="com.tap.DaoImpl.RestaurantDaoImpl" %>

<%
    RestaurantDaoImpl rdi = new RestaurantDaoImpl();
    List<Restaurant> restaurantList = rdi.getAllRestaurants();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Select Restaurant</title>
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
        .restaurant-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
        }
        .restaurant-card {
            background: white;
            border: 1px solid #ccc;
            border-radius: 10px;
            width: 320px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            text-align: left;
        }
        .restaurant-card img {
            width: 100%;
            height: 180px;
            object-fit: cover;
            border-radius: 8px;
            margin-bottom: 10px;
        }
        .restaurant-card h3 {
            color: #007bff;
            margin: 0;
        }
        .restaurant-card p {
            margin: 5px 0;
            color: #555;
        }
        .restaurant-card form {
            margin-top: 10px;
        }
        .restaurant-card button {
            padding: 8px 12px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .restaurant-card button:hover {
            background-color: #218838;
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
     <a href="UserDashboard.html">Home</a>
      <a href="restaurant.jsp">Restaurants</a>
      <a href="cart.jsp">Cart</a>
      <a href="orderHistory.jsp">Order History</a>
      <a href="signin.html">Logout</a>
    </div>
  
  </div>
    <h2>Select a Restaurant</h2>
    <div class="restaurant-container">
        <% for (Restaurant r : restaurantList) { %>
            <div class="restaurant-card">
                <img src="<%= r.getImagePath() %>" alt="Restaurant Image">
                <h3><%= r.getName() %></h3>
                <p><strong>Address:</strong> <%= r.getAddress() %></p>
                <p><strong>Phone:</strong> <%= r.getPhonenumber() %></p>
                <p><strong>Cuisine:</strong> <%= r.getCuisineType() %></p>
                <p><strong>Rating:</strong> <%= r.getRating() %>/5</p>
                <p><strong>Delivery Time:</strong> <%= r.getDeliveryTime() %></p>
                <form method="post" action="selectRestaurant">
                    <input type="hidden" name="restaurantId" value="<%= r.getRestaurantid() %>" />
                    <button type="submit">View Menu</button>
                </form>
            </div>
        <% } %>
    </div>
</body>
</html>