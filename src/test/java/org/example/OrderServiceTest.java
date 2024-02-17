package org.example;

import org.example.Order.Order;
import org.example.Order.OrderDaoImpl;
import org.example.Product.ProductDaoImpl;
import org.example.User.User;
import org.example.User.UserDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OrderServiceTest {
    private OrderDaoImpl orderDao;
    private ProductDaoImpl productsDAO;
    private UserDaoImpl userDao;
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderDao = new OrderDaoImpl();
        productsDAO = new ProductDaoImpl();
        userDao = new UserDaoImpl();
        orderService = new OrderService();
    }

    @Test
    public void testPlaceOrder() {

        User user = userDao.getUserById(241L);
        double totalAmount = productsDAO.getTotalPriceForUser(user.getId());


        orderService.placeOrder(user);


        List<Order> orders = orderDao.getAllOrdersById(user.getId());

        Assertions.assertEquals(1, orders.size());

        Order placedOrder = orders.getFirst();

        Assertions.assertEquals(user.getId(), placedOrder.getUserId());

        Assertions.assertNotNull(placedOrder.getProductNames());
        //Assertions.assertEquals("Сигареты \"Marlboro Gold\", Пиво \"Bud\"", placedOrder.getProductNames()); --> expected: <РЎРёРіР°СЂРµС‚С‹ "Marlboro Gold", РџРёРІРѕ "Bud"> but was: <Сигареты "Marlboro Gold", Пиво "Bud">

        Assertions.assertEquals(totalAmount, placedOrder.getTotalAmount());
        Assertions.assertEquals(135.55, totalAmount);
    }
}

