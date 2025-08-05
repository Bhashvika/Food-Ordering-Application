package com.tap.DaoImpl;

import com.tap.DB.ConnectDB;
import com.tap.Dao.OrderItemDao;
import com.tap.models.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDaoImpl implements OrderItemDao {

    String insertQuery = "INSERT INTO orderitem (orderid, menuid, quantity, totalamount) VALUES (?, ?, ?, ?)";
    String selectByOrderIdQuery = "SELECT * FROM orderitem WHERE orderid=?";

    @Override
    public void insertOrderItem(OrderItem item) {
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(insertQuery)) {

            pstmt.setInt(1, item.getOrderId());
            pstmt.setInt(2, item.getMenuId());
            pstmt.setInt(3, item.getQuantity());
            pstmt.setDouble(4, item.getTotalAmount());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        List<OrderItem> items = new ArrayList<>();
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(selectByOrderIdQuery)) {

            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem(
                        rs.getInt("orderitemid"),
                        rs.getInt("orderid"),
                        rs.getInt("menuid"),
                        rs.getInt("quantity"),
                        rs.getDouble("totalamount")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}
