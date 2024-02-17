package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Order.OrderDaoImpl;
import org.example.Order.Order;
import org.example.Product.ProductDaoImpl;
import org.example.ShoppingCart.ShoppingCartDaoImpl;
import org.example.User.User;
import org.example.User.UserDaoImpl;

import java.util.List;

public class OrderService {
    private OrderDaoImpl orderDao = new OrderDaoImpl();
    private ShoppingCartDaoImpl cartDAO = new ShoppingCartDaoImpl();
    private ProductDaoImpl productsDAO = new ProductDaoImpl();
    private UserDaoImpl userDao = new UserDaoImpl();
    private static final Logger logger = LogManager.getLogger(OrderService.class);


    public void placeOrder(User user) {
        logger.info("Placing order for user {}", user.getName());

        List<String> products = cartDAO.getAllProductsFromCart(user);
        logger.debug("Received products from cart: {}", products);

        double totalAmount = productsDAO.getTotalPriceForUser(user.getId());
        logger.debug("Total price for user's products: {}", totalAmount);

        Order order = new Order(user, String.join(", ", products), totalAmount);

        orderDao.createOrder(order);
        logger.info("Order saved successfully");

        cartDAO.removeAllProductsFromCart(user);
        logger.info("All products removed from shopping cart");


    }
}