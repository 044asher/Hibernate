package org.example.User;

import jakarta.persistence.*;
import org.example.Order.Order;
import org.example.ShoppingCart.ShoppingCart;
import org.example.UserDetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_name")
    private String name;
    private String surname;
    private String email;
    private Integer age;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private ShoppingCart shoppingCart;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserDetails userDetails;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;
    public User(){
    }
    public User(String name, String surname, String email, int age){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        orders = new ArrayList<>();
    }
    public void addOrder(Order order){
        order.setUser(this);
        orders.add(order);
    }
    public void removeOrder(Order order){
        orders.remove(order);
    }
    public Long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public List<Order> getOrders(){
        return orders;
    }
    public void setOrders(List<Order> orders){
        this.orders = orders;
    }
    @Override
    public String toString() {
        return "Users{" +
                "user_id=" + id +
                ", user_name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
