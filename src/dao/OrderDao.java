package dao;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDao {
    private SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
    private Transaction transaction;

    public Order getOrderById(int id) {
        Session session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        Order order = session.get(Order.class, id);
        transaction.commit();
        return order;
    }

    public void saveOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
    }

    public List<Order> getAllOrders(int userId) {
        Session session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("from Order o where o.user.id=:id",Order.class);
        query.setParameter("id", userId);
        List<Order> orders = query.list();
        transaction.commit();
        return orders;
    }

    public void deleteOrder(int id) {
        Session session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        Query<Order> query = session.createQuery("delete from Order o where o.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

}
