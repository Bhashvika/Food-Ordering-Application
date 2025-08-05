<%@ page import="java.util.List" %>
<%@ page import="com.tap.models.Order" %>

<%

List<Order> orderHistory = (List<Order>) request.getAttribute("orderHistory");

%>



<!DOCTYPE html>
<html>
<head>
    <title>Order History</title>
    <style>
      
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
        h2 {
            text-align: center;
            color: #333;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 12px 15px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: #fff;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .no-orders {
            text-align: center;
            font-size: 18px;
            color: #777;
            margin-top: 30px;
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
      <a href="restaurant.html">Restaurants</a>
      <a href="cart.html">Cart</a>
      <a href="orderHistory.jsp">Order History</a>
      <a href="signin.html">Logout</a>
    </div>
  
  </div>
    <h2>Your Order History</h2>
      <%if(orderHistory == null){
        out.println("orderHistory is NULL");
    } else {
        out.println("orderHistory size: " + orderHistory.size());
    } %>
    <table border="1">
        <tr>
            <th>Order ID</th>
            <th>Date</th>
            <th>Total Amount</th>
            <th>Status</th>
            <th>Payment Mode</th>
        </tr>

        <% if(orderHistory != null && !orderHistory.isEmpty()) { 
            for(Order order : orderHistory) { 
        %>
            <tr>
                <td><%= order.getOrderId() %></td>
                <td><%= order.getOrderDate() %></td>
                <td><%= order.getTotalAmount() %></td>
                <td><%= order.getStatus() %></td>
                <td><%= order.getPaymentMode() %></td>
            </tr>
        <% 
            } 
        } else { 
        %>
            <tr><td colspan="5">No orders found.</td></tr>
        <% } %>
    </table>
</body>
</html>
