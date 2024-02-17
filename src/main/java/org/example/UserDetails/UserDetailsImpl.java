package org.example.UserDetails;

import jakarta.persistence.NoResultException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDetailsImpl implements UserDetailsDao{
    private static final Logger logger = LogManager.getLogger(UserDetailsImpl.class);
    @Override
    public void createUserDetails(UserDetails userDetails) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            session.persist(userDetails);
            transaction.commit();
            logger.info("User details created successfully: {}", userDetails);
        } catch (HibernateException e) {
            logger.error("Error creating user details: {}", e.getMessage());
        }
    }

    @Override
    public List<UserDetails> getAllUserDetails() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM UserDetails", UserDetails.class).list();
        }catch (HibernateException | NoResultException e) {
            logger.error("Error getting all user details: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public UserDetails getUserDetailsById(Long id) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return session.get(UserDetails.class, id);
        }catch (HibernateException e) {
            logger.error("Error getting user details by id {}: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public void updateUserDetails(UserDetails userDetails) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(userDetails);
            transaction.commit();
            logger.info("User details updated successfully: {}", userDetails);
        } catch (HibernateException e) {
            logger.error("Error updating user details: {}", e.getMessage());
        }
    }

    @Override
    public void deleteUserDetails(UserDetails userDetails) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(userDetails);
            transaction.commit();
            logger.info("User details deleted successfully: {}", userDetails);
        } catch (HibernateException e) {
            logger.error("Error deleting user details: {}", e.getMessage());
        }
    }
}
