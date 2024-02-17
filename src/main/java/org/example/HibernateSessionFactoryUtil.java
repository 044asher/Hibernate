package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Product.Product;
import org.example.User.User;
import org.example.Order.Order;
import org.example.ShoppingCart.ShoppingCart;
import org.example.UserDetails.UserDetails;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(HibernateSessionFactoryUtil.class);

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure(); //Указывает на файл hibernate.cfg.xml который содержит настройки подключения к базе данных
                configuration.addAnnotatedClass(User.class);//Передаем @Entity объекты. Это позволит Hibernate определить сопоставление объектов с данными в базе данных.
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(ShoppingCart.class);
                configuration.addAnnotatedClass(UserDetails.class);
                configuration.addAnnotatedClass(Product.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()); //Объект, который позволяет настроить различные службы Hibernate, такие, как подключение к базе данных, кэширование и т. д. applySettings используется для применения настроек из объекта конфигурации.
                sessionFactory = configuration.buildSessionFactory(builder.build()); //Строим фабрику сессий, которая является основным интерфейсом для взаимодействия с Hibernate. buildSessionFactory() принимает созданный ранее объект строителя реестра служб и строит фабрику сессий на основе заданных настроек.

            } catch (Exception e) {
                logger.error("Session Factory error: " + e.getMessage());
            }
        }
        return sessionFactory;
    }
}
