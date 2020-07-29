package dao;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Lazy
@Scope("prototype")
public class OrderDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Order getOrderById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Order order = session.get(Order.class, id);
        transaction.commit();
        return order;
    }

    public void saveOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
    }

    public List<Order> getAllOrders(int userId) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Order o where o.user.id=:id", Order.class);
        query.setParameter("id", userId);
        List<Order> orders = query.list();
        transaction.commit();
        return orders;
    }

    public void deleteOrder(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query<Order> query = session.createQuery("delete from Order o where o.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

}
