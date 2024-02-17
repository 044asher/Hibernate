package org.example.Product;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name")
    private String name;
    private Double price;
    private String description;
    public Product(){
    }
    public Product(String name, Double price, String description){
        this.name = name;
        this.price = price;
        this.description = description;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Products{" +
                "product_id = " + id +
                ", product_name ='" + name + '\'' +
                ", price ='" + price + '\'' +
                ", description ='" + description +
                '}';
    }
}
