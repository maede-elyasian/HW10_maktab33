package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySessionFactory {
    public static SessionFactory getSessionFactory() {
    SessionFactory sessionFactory = new Configuration().configure("resource/hibernate.cfg.xml").buildSessionFactory();
        return sessionFactory;
    }
}
