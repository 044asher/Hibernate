package org.example.Order;

import org.example.User.User;

import java.util.List;

public interface OrderDao {
    void createOrder(Order order);
    List<Order> getAllOrdersById(Long userId);
    List<Order> getAllOrders();
}
