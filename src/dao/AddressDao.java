package dao;

import entity.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class AddressDao {
    private SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
    private Transaction transaction;

    public Address getAddressById(int id) {
        Session session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        Address address = session.get(Address.class, id);
        transaction.commit();
        return address;
    }

    public void saveAddress(Address address) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(address);
        transaction.commit();
    }

    public void deleteAddress(int id) {
        Session session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Address where id=:id", Address.class);
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
    }

    public List<Address> getAllAddress() {
        Session session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        List<Address> addresses = session.createQuery("from Address", Address.class).list();
        transaction.commit();
        return addresses;
    }

}
