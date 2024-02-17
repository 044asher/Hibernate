package org.example.Order;

import jakarta.persistence.*;
import org.example.User.User;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "product_names")
    private String productNames;
    @Column(name = "total_amount")
    private double totalAmount;
    public Order(){
    }
    public Order(User user, String productNames, double totalAmount){
        this.user = user;
        this.productNames = productNames;
        this.totalAmount = totalAmount;
    }
    public Long getOrderId(){
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Long getUserId(){
        return user.getId();
    }

    public String getProductNames() {
        return productNames;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    @Override
    public String toString(){
        return "Orders {" +
                "order_id: " + id +
                ", user_id: " + user.getId() +
                ", product_names: " + productNames +
                ", total_amount: " + totalAmount + '}';
    }
}
