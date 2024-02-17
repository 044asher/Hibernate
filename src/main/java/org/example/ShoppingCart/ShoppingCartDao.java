package org.example.ShoppingCart;

import org.example.Product.Product;
import org.example.User.User;

import java.util.List;

public interface ShoppingCartDao {
    public void addProductToCart(User user, String product);
    public void removeProductFromCart(User user, String product);
    public List<String> getAllProductsFromCart(User user);
    public void removeAllProductsFromCart(User user);
}
