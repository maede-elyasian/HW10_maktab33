package utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Embeddable;

@Component
@Lazy
@Scope("prototype")
@Embeddable
public class GetIdByTable {
    @Autowired
    private SessionFactory sessionFactory ;

    public int getId(String tableName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String getId = "SELECT max(id) from " + tableName;
        NativeQuery query = session.createNativeQuery(getId);
        transaction.commit();
        return (int) query.uniqueResult();
    }
}
