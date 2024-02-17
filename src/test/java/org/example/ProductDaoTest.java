package org.example;

import org.example.Product.ProductDaoImpl;
import org.example.Product.Product;
import org.junit.jupiter.api.*;

import java.util.List;

public class ProductDaoTest {
    private ProductDaoImpl productsDAO;
    private Product testProduct;

    @BeforeEach
    public void setUp() {
        productsDAO = new ProductDaoImpl();
        testProduct = new Product();
        testProduct.setName("TestProduct");
        testProduct.setPrice(10.0);
        testProduct.setDescription("TestDescription");
        productsDAO.createProduct(testProduct);
    }
    @AfterEach
    public void clearTests(){
        productsDAO.deleteProduct(testProduct);
    }

    @Test
    public void testCreateProduct() {
        Product newProduct = new Product();
        newProduct.setName("NewProduct");
        newProduct.setPrice(20.0);
        newProduct.setDescription("NewDescription");
        productsDAO.createProduct(newProduct);

        Product retrievedProduct = productsDAO.getProductById(newProduct.getId());

        Assertions.assertEquals(newProduct.getId(), retrievedProduct.getId());
        Assertions.assertEquals(newProduct.getName(), retrievedProduct.getName());
        Assertions.assertEquals(newProduct.getPrice(), retrievedProduct.getPrice());
        Assertions.assertEquals(newProduct.getDescription(), retrievedProduct.getDescription());

        productsDAO.deleteProduct(newProduct);
    }

    @Test
    public void testGetProductById() {
        Product retrievedProduct = productsDAO.getProductById(testProduct.getId());
        Assertions.assertEquals(testProduct.getId(), retrievedProduct.getId());
    }

    @Test
    public void testGetAllProducts() {
        List<Product> productsList = productsDAO.getAllProducts();
        Assertions.assertFalse(productsList.isEmpty());

        Product retrievedProduct = productsDAO.getProductById(testProduct.getId());
        Assertions.assertNotNull(retrievedProduct);
    }

    @Test
    public void testUpdateProduct() {
        testProduct.setPrice(15.0);
        testProduct.setDescription("UpdatedDescription");
        productsDAO.updateProduct(testProduct);
        Assertions.assertEquals(15.0, testProduct.getPrice());
        Assertions.assertEquals("UpdatedDescription", testProduct.getDescription());
    }

    @Test
    @Disabled
    public void testDeleteProduct() {
        Product retrievedProduct = productsDAO.getProductById(testProduct.getId());
        Assertions.assertEquals(testProduct.getId(), retrievedProduct.getId());

        productsDAO.deleteProduct(testProduct);
        retrievedProduct = productsDAO.getProductById(testProduct.getId());
        Assertions.assertNull(retrievedProduct);
    }


}
