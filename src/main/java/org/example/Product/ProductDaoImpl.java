package org.example.Product;

import jakarta.persistence.NoResultException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDaoImpl implements ProductDao{
    private static final Logger logger = LogManager.getLogger(ProductDaoImpl.class);
    @Override
    public void createProduct(Product product) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            session.persist(product);
            transaction.commit();
            logger.info("Product {} created successfully", product.getName());
        } catch (HibernateException e) {
            logger.error("Error creating product: {}", e.getMessage());
        }
    }

    @Override
    public Product getProductById(Long id) {
        logger.info("Getting product by id {}", id);
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return session.get(Product.class, id);
        }catch (HibernateException e) {
            logger.error("Error getting product by id {}: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        }catch (HibernateException | NoResultException e) {
            logger.error("Error getting all products: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public void updateProduct(Product product) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(product);
            transaction.commit();
            logger.info("Product {} updated successfully", product.getName());
        } catch (HibernateException e) {
            logger.error("Error updating product: {}", e.getMessage());
        }
    }

    @Override
    public void deleteProduct(Product product) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(product);
            transaction.commit();
            logger.info("Product {} deleted successfully", product.getName());
        } catch (HibernateException e) {
            logger.error("Error deleting product: {}", e.getMessage());
        }
    }
    public double getProductPrice(String product){
    double price = 0;
    try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
        Query<Double> query = session.createQuery("select price from Product where name = :product", Double.class);
        query.setParameter("product", product);
        price = query.getSingleResult();
    } catch (NoResultException | HibernateException e){
        logger.error("Product with name {} not found", product);
    }
    return price;
    }

    public double getTotalPriceForUser(Long id){
        double totalPrice = 0;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Query<String> query = session.createQuery("select products from ShoppingCart where user.id = :id", String.class);
            query.setParameter("id", id);
            String productsString = query.getSingleResult();
            if(productsString != null){
                String[] productNames = productsString.split(", ");
                for (String product : productNames){
                    totalPrice += getProductPrice(product);
                }
            }
        } catch (HibernateException | NoResultException e){
            logger.error("Error by getting total price for user {}", id);
        }
        return totalPrice;
    }
}
