package org.example;

import org.example.Order.Order;
import org.example.Order.OrderDaoImpl;
import org.example.Product.Product;
import org.example.Product.ProductDaoImpl;
import org.example.ShoppingCart.ShoppingCartDaoImpl;
import org.example.User.User;
import org.example.User.UserDaoImpl;
import org.example.UserDetails.UserDetails;
import org.example.UserDetails.UserDetailsImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        User newUser = new User("hibernate", "Testing", "sdfsd@gmail.com", 55);
        UserDaoImpl userDao = new UserDaoImpl();

        userDao.createUser(newUser);
        List<User> users = userDao.getAllUsers();
        for(User user : users){
            System.out.println(user);
        }



        newUser.setEmail("mr. Changed");
        userDao.updateUser(newUser);
        System.out.println(userDao.getUserById(newUser.getId()));


        userDao.deleteUser(newUser);

        System.out.println("---------------------------------------------------------");

        UserDetailsImpl userDetailsDao = new UserDetailsImpl();
        UserDetails newUserDetails = new UserDetails(userDao.getUserById(5L), "address 11/1", "+345325532532");
        userDetailsDao.createUserDetails(newUserDetails);

        List<UserDetails> allUserDetails = userDetailsDao.getAllUserDetails();
        for (UserDetails details : allUserDetails) {
            System.out.println(details);
        }


        newUserDetails.setAddress("Changed 1/12");
        userDetailsDao.updateUserDetails(newUserDetails);
        System.out.println(userDetailsDao.getUserDetailsById(newUserDetails.getId()));

        userDetailsDao.deleteUserDetails(newUserDetails);


        System.out.println("----------------------------------------------------");

        ProductDaoImpl productDao = new ProductDaoImpl();
        Product newProduct = new Product("Hibernate", 101.24, "Description");

        productDao.createProduct(newProduct);

        List<Product> products = productDao.getAllProducts();
        for(Product product : products){
            System.out.println(product);
        }


        newProduct.setDescription("Changed Description");
        productDao.updateProduct(newProduct);

        System.out.println(productDao.getProductById(newProduct.getId()));

        productDao.deleteProduct(newProduct);


        System.out.println("---------------------------------------------------------");


        ShoppingCartDaoImpl cartDao = new ShoppingCartDaoImpl();
        System.out.println(cartDao.getAllProductsFromCart(userDao.getUserById(1L)));

        //cartDao.addProductToCart(userDao.getUserById(1L), "Product");
        System.out.println(cartDao.getAllProductsFromCart(userDao.getUserById(1L)));

        //cartDao.removeProductFromCart(userDao.getUserById(1L), "Bear \"Bud\"");
        System.out.println(cartDao.getAllProductsFromCart(userDao.getUserById(1L)));

        //cartDao.removeAllProductsFromCart(userDao.getUserById(1L));


        OrderDaoImpl orderDao = new OrderDaoImpl();
        Order newOrder = new Order(userDao.getUserById(2L), "Product1, Product2", 100.00);

        //orderDao.createOrder(newOrder);
        System.out.println("_______________________________________________");
        System.out.println(orderDao.getAllOrdersById(2L));
        List<Order> orders = orderDao.getAllOrders();
        for(Order order : orders){
            System.out.println(order);
        }



        OrderService orderService = new OrderService();
        User user = userDao.getUserById(241L);
        orderService.placeOrder(user);
    }
}