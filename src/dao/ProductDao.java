package dao;

import entity.Product;
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
public class ProductDao {
    @Autowired
    private SessionFactory sessionFactory;


    public Product getProductById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.get(Product.class, id);
        transaction.commit();
        return product;
    }

    public void deleteProduct(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Product where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
    }

    public List<Product> getAllProducts() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List products = session.createQuery("from Product", Product.class).list();
        transaction.commit();
        return products;
    }

    public void updateProduct(int id, int productNumber) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Product p set p.productNumber=:num where p.id=:id")
                .setParameter("num", productNumber)
                .setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
    }
}
