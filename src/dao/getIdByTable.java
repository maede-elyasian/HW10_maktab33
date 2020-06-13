package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.persistence.Embeddable;

@Embeddable
public class getIdByTable {
    private static SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
    private static Transaction transaction;

    public static int getId(String tableName) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        String getId = "SELECT max(id) from " + tableName;
        NativeQuery query = session.createNativeQuery(getId);
        transaction.commit();
        return (int) query.uniqueResult();
    }
}
