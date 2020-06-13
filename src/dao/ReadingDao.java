package dao;

import entity.Reading;
import jdk.internal.org.objectweb.asm.util.TraceAnnotationVisitor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReadingDao extends ProductDao {
    private SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
    private Transaction transaction;

    public Reading getReadingById(int id) {
        Session session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        Reading reading = session.get(Reading.class, id);
        transaction.commit();
        return reading;
    }

    public void deleteReading(int id) {
        Session session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        Query<Reading> query = session.createQuery("delete from Reading where id=:id", Reading.class);
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
    }

    public List<Reading> getAllReadings() {
        Session session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        List readings = session.createQuery("from Reading", Reading.class).list();
        transaction.commit();
        return readings;
    }
}
