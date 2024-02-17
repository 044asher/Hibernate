package org.example.User;

import jakarta.persistence.NoResultException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoImpl implements UserDao {
    //ALTER TABLE users AUTO_INCREMENT = 6;
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public void createUser(User user) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            session.persist(user);
            transaction.commit();
            logger.info("User created successfully: {}", user);
        } catch (HibernateException e) {
            logger.error("Error creating user: {}", e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (HibernateException | NoResultException e) {
            logger.error("Error getting all users: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public User getUserById(Long id) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return session.get(User.class, id);
        }catch (HibernateException e) {
            logger.error("Error getting user by id {}: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public void updateUser(User user) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(user); // УСТАРЕЛО, почитать про merge как о замене
            transaction.commit();
            logger.info("User updated successfully: {}", user);
        }catch (HibernateException e) {
            logger.error("Error updating user: {}", e.getMessage());
        }
    }

    @Override
    public void deleteUser(User user) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
            logger.info("User deleted successfully: {}", user);
        }catch (Exception e) {
            logger.error("Error deleting user: {}", e.getMessage());
        }
    }
}
