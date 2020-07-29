package dao;

import entity.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import service.userService.validation.AddressValidationService;

import java.util.List;

@Component
@Lazy
@Scope("prototype")
public class AddressDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Address getAddressById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Address address = session.get(Address.class, id);
        transaction.commit();
        return address;
    }

    public void saveAddress(Address address) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (AddressValidationService.isValidAddress(address)) {
            session.save(address);
            transaction.commit();
            session.close();
        }
    }

    public void deleteAddress(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Address where id=:id", Address.class);
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
    }

    public List<Address> getAllAddress() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Address> addresses = session.createQuery("from Address", Address.class).list();
        transaction.commit();
        return addresses;
    }

}
