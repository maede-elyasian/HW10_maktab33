package dao;

import entity.ElectronicDevice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ElectronicDeviceDao extends ProductDao {
    private SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
    private Transaction transaction;

    public ElectronicDevice getElcDevById(int id) {
        Session session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        ElectronicDevice electronicDevice = session.get(ElectronicDevice.class, id);
        transaction.commit();
        return electronicDevice;
    }

    public void savetElectronicDev(ElectronicDevice electronicDevice) {
        Session session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(electronicDevice);
        transaction.commit();
    }

    public void deleteElectronicDev(int id) {
        Session session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("delete from ElectronicDevice where id=:id", ElectronicDevice.class);
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
    }

    public List<ElectronicDevice> getAllDevices() {
        Session session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        List list = session.createQuery("from ElectronicDevice", ElectronicDevice.class).list();
        transaction.commit();
        return list;
    }
}
