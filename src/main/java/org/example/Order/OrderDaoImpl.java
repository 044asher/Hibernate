package org.example.Order;

import jakarta.persistence.NoResultException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDaoImpl implements OrderDao{
    private static final Logger logger = LogManager.getLogger(OrderDaoImpl.class);
    @Override
    public void createOrder(Order order) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            session.persist(order);
            transaction.commit();
            logger.info("Order created successfully: {}", order);
        } catch (HibernateException e) {
            logger.error("Error creating order: {}", e.getMessage());
        }
    }

    @Override
    public List<Order> getAllOrdersById(Long userId) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Query<Order> query = session.createQuery("FROM Order WHERE user.id = :userId", Order.class);
            query.setParameter("userId", userId);
            return query.list();
        }catch (HibernateException | NoResultException e) {
            logger.error("Error getting all orders by user id {}: {}", userId, e.getMessage());
            return null;
        }
    }

    @Override
    public List<Order> getAllOrders() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Order", Order.class).list();
        }catch (HibernateException | NoResultException e) {
            logger.error("Error getting all orders: {}", e.getMessage());
            return null;
        }
    }
}
