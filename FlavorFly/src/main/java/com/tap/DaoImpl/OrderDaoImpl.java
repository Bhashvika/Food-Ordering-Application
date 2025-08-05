package com.tap.DaoImpl;

import com.tap.DB.ConnectDB;
import com.tap.Dao.OrderDao;
import com.tap.models.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    String insertQuery = "INSERT INTO orders (restaurantId, userId, orderDate, totalAmount, status, paymentMode) VALUES (?, ?, ?, ?, ?, ?)";
    String selectByIdQuery = "SELECT * FROM orders WHERE orderId=?";
    String selectByUserQuery = "SELECT * FROM orders WHERE userId=?";
    String selectAllQuery = "SELECT * FROM orders";
    String updateStatusQuery = "UPDATE orders SET status=? WHERE orderId=?";
    String deleteQuery = "DELETE FROM orders WHERE orderId=?";

    @Override
    public int insertOrder(Order o) {
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, o.getRestaurantId());
            pstmt.setInt(2, o.getUserId());
            pstmt.setTimestamp(3, new Timestamp(o.getOrderDate().getTime()));
            pstmt.setDouble(4, o.getTotalAmount());
            pstmt.setString(5, o.getStatus());
            pstmt.setString(6, o.getPaymentMode());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int orderId = rs.getInt(1);
                        o.setOrderId(orderId);
                        return orderId;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;  // Indicate failure
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(selectByIdQuery)) {

            pstmt.setInt(1, orderId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    order = mapResultSetToOrder(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> list = new ArrayList<>();
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(selectByUserQuery)) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToOrder(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(selectAllQuery)) {

            while (rs.next()) {
                list.add(mapResultSetToOrder(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateOrderStatus(int orderId, String status) {
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(updateStatusQuery)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, orderId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(deleteQuery)) {

            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Order mapResultSetToOrder(ResultSet rs) throws SQLException {
        return new Order(
                rs.getInt("orderId"),
                rs.getInt("restaurantId"),
                rs.getInt("userId"),
                rs.getTimestamp("orderDate"),
                rs.getDouble("totalAmount"),
                rs.getString("status"),
                rs.getString("paymentMode")
        );
    }
}
