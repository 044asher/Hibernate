package org.example.ShoppingCart;

import jakarta.persistence.*;
import org.example.User.User;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @Column(name = "cart_id")
    private Long cartId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "products")
    private String products;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "quantity")
    private int quantity;

    public ShoppingCart() {
    }

    public ShoppingCart(User user, String products, double totalAmount, int quantity) {
        this.user = user;
        this.products = products;
        this.totalAmount = totalAmount;
        this.quantity = quantity;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
