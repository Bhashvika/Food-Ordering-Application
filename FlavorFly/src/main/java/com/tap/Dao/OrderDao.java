package com.tap.Dao;

import com.tap.models.Order;
import java.util.List;

public interface OrderDao {
    int insertOrder(Order order);
    Order getOrderById(int orderId);
    List<Order> getOrdersByUserId(int userId);
    List<Order> getAllOrders();
    void updateOrderStatus(int orderId, String status);
    void deleteOrder(int orderId);
}
