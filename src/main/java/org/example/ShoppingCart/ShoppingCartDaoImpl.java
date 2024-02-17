package org.example.ShoppingCart;

import jakarta.persistence.NoResultException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.HibernateSessionFactoryUtil;
import org.example.User.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingCartDaoImpl implements ShoppingCartDao {

    Logger logger = LogManager.getLogger(ShoppingCartDaoImpl.class);

    @Override
    public void addProductToCart(User user, String product) {
        logger.info("Adding product {} to {}'s shopping cart", product, user.getName());
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            ShoppingCart shoppingCart = user.getShoppingCart();
            shoppingCart.setProducts(shoppingCart.getProducts() + ", " + product);
            shoppingCart.setQuantity(shoppingCart.getQuantity() + 1);

            session.merge(shoppingCart);
            transaction.commit();
        } catch (HibernateException e){
            logger.error("Error adding product to shopping cart: {}", e.getMessage());
        }
    }

    @Override
    public void removeProductFromCart(User user, String product) {
        logger.info("Removing product {} from {}'s shopping cart", product, user.getName());
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            ShoppingCart shoppingCart = user.getShoppingCart();
            String products = shoppingCart.getProducts();
            products = products.replace(product + ", ", "");
            products = products.replace(", " + product, "");
            shoppingCart.setProducts(products);
            shoppingCart.setQuantity(shoppingCart.getQuantity() - 1);

            session.merge(shoppingCart);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Error removing product from shopping cart: {}", e.getMessage());
        }
    }

    @Override
    public List<String> getAllProductsFromCart(User user) {
        logger.info("Getting all products from {}'s shopping cart", user.getName());
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<String> query = session.createQuery("SELECT s.products FROM ShoppingCart s WHERE s.user = :user", String.class);
            query.setParameter("user", user);
            List<String> products = new ArrayList<>();
            List<String> result = query.getResultList();
            for (String productString : result) {
                String[] productArray = productString.split(", ");
                products.addAll(Arrays.asList(productArray));
            }
            return products;
        } catch (HibernateException | NoResultException e) {
            logger.error("Error getting all products from shopping cart: {}", e.getMessage());
            return null;
        }
    }


    @Override
    public void removeAllProductsFromCart(User user) {
        logger.info("Removing all products from {}'s shopping cart", user.getName());
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            ShoppingCart shoppingCart = user.getShoppingCart();
            shoppingCart.setProducts("");
            shoppingCart.setQuantity(0);
            shoppingCart.setTotalAmount(0);

            session.merge(shoppingCart);
            transaction.commit();
        }catch (HibernateException e) {
            logger.error("Error removing all products from shopping cart: {}", e.getMessage());
        }
    }
}
