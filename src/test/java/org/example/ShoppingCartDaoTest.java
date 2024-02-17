package org.example;

import org.example.ShoppingCart.ShoppingCartDaoImpl;
import org.example.ShoppingCart.ShoppingCartDao;
import org.example.User.User;
import org.example.User.UserDao;
import org.example.User.UserDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShoppingCartDaoTest {
    private ShoppingCartDao cartDao;
    private User testUser;
    private String testProduct1 = "product3";
    private String testProduct2 = "product4";

    @BeforeEach
    public void setUp() {
        cartDao = new ShoppingCartDaoImpl();
        UserDao userDao = new UserDaoImpl();
        testUser = userDao.getUserById(300L);
        cartDao.removeAllProductsFromCart(testUser);
    }
    @Test
    public void testAddProductToCart() {
        cartDao.addProductToCart(testUser, testProduct1);

        List<String> productsInCart = cartDao.getAllProductsFromCart(testUser);

        boolean found = false;
        for (String product : productsInCart) {
            if (product.endsWith(testProduct1)) {
                found = true;
                break;
            }
        }
        Assertions.assertTrue(found);
    }

    @Test
    public void testRemoveProductFromCart() {
        cartDao.addProductToCart(testUser, testProduct1);
        cartDao.removeProductFromCart(testUser, testProduct1);

        List<String> productsInCart = cartDao.getAllProductsFromCart(testUser);
        boolean found = false;
        for (String product : productsInCart) {
            if (product.endsWith(testProduct1)) {
                found = true;
                break;
            }
        }
        Assertions.assertFalse(found);
    }

    @Test
    public void testGetAllProductsFromCart() {
        cartDao.addProductToCart(testUser, testProduct1);

        List<String> productsInCart = cartDao.getAllProductsFromCart(testUser);
        boolean found = false;

        for (String product : productsInCart) {
            if (product.endsWith(testProduct1)) {
                found = true;
                break;
            }
        }
        Assertions.assertTrue(found);
    }

    @Test
    public void testRemoveAllProductsFromCart() {
        cartDao.addProductToCart(testUser, testProduct1);
        cartDao.addProductToCart(testUser, testProduct2);

        cartDao.removeAllProductsFromCart(testUser);

        List<String> productsInCart = cartDao.getAllProductsFromCart(testUser);

        Assertions.assertFalse(productsInCart.contains(testProduct1));
        Assertions.assertFalse(productsInCart.contains(testProduct2));
    }

}
