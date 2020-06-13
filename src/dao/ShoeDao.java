package dao;

import entity.Shoe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class ShoeDao extends ProductDao {
    private SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
    private Transaction transaction;

    public Shoe getShoeById(int id) {
        Session session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        Shoe shoe = session.get(Shoe.class, id);
        transaction.commit();
        return shoe;
    }

    public void deleteShoe(int id) {
        Session session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Shoe where id=:id", Shoe.class);
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
    }

    public List<Shoe> getAllShoes() {
        Session session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        List list = session.createQuery("from Shoe", Shoe.class).list();
        transaction.commit();
        return list;
    }
}
