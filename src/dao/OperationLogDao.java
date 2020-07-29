package dao;

import entity.OperationLog;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@Lazy
@Scope("prototype")
public class OperationLogDao {
    @Autowired
    private SessionFactory sessionFactory;


    public void productLog(String product, User user, String operation) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String operation2 = operation + " " + product;
        OperationLog operationLog = new OperationLog(operation2, user, LocalDate.now(), LocalTime.now());
        session.save(operationLog);
        session.getTransaction().commit();
        session.close();
    }


    public void login(User user, String operation) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        OperationLog operationLog = new OperationLog(operation, user, LocalDate.now(), LocalTime.now());
        session.saveOrUpdate(operationLog);
        transaction.commit();
        session.close();
    }


    public void purchaseLog(User user, double totalPrice, String operation) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String operation2 = operation + " " + totalPrice;
        OperationLog operationLog = new OperationLog(operation2, user, LocalDate.now(), LocalTime.now());
        session.save(operationLog);
        session.getTransaction().commit();
        session.close();

    }

    public List<OperationLog> getAllOperations(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("select * from operation_log where user_id=?", OperationLog.class);
        nativeQuery.setParameter(1, id);
        List<OperationLog> list = nativeQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }

}
