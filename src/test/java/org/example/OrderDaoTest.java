package org.example;

import org.example.Order.OrderDaoImpl;
import org.example.Order.Order;
import org.example.User.UserDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OrderDaoTest {
    private OrderDaoImpl orderDao;
    private Order testOrder;

    @BeforeEach
    public void setUp() {
        orderDao = new OrderDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();
        testOrder = new Order();
        testOrder.setUser(userDao.getUserById(2L));
        testOrder.setProductNames("Product1, Product2");
        testOrder.setTotalAmount(100.0);
    }

    @Test
    public void testSaveOrder() {
        orderDao.createOrder(testOrder);
        Order retrievedOrder = orderDao.getAllOrdersById(testOrder.getUserId()).getLast();

        Assertions.assertEquals(retrievedOrder.getOrderId(), testOrder.getOrderId());
        Assertions.assertEquals(testOrder.getUserId(), retrievedOrder.getUserId());
        Assertions.assertEquals(testOrder.getProductNames(), retrievedOrder.getProductNames());
        Assertions.assertEquals(testOrder.getTotalAmount(), retrievedOrder.getTotalAmount());
    }

    @Test
    public void testGetAllOrdersByUser() {
        List<Order> ordersList = orderDao.getAllOrdersById(testOrder.getUserId());
        Assertions.assertFalse(ordersList.isEmpty());

        Order retrievedOrder = ordersList.getFirst();
        Assertions.assertEquals(testOrder.getUserId(), retrievedOrder.getUserId());
    }

    @Test
    public void testGetAllOrders() {

        List<Order> orderList = orderDao.getAllOrders();
        Assertions.assertFalse(orderList.isEmpty());

        Order retrievedOrder = orderDao.getAllOrdersById(testOrder.getUserId()).getFirst();
        Assertions.assertNotNull(retrievedOrder);

        Order retrievedOrder1 = orderDao.getAllOrdersById(2L).getFirst();
        Assertions.assertNotNull(retrievedOrder1);
    }

}
