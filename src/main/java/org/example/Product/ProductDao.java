package org.example.Product;

import java.util.List;

public interface ProductDao {
    void createProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(Product product);
}
