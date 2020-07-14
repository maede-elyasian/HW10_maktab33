package dao;

import entity.OperationLog;
import entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class OperationLogDao {
    @Autowired
    private static SessionFactory sessionFactory = MySessionFactory.getSessionFactory();

    public void productLog(String product, User user, String operation) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String operation2 = operation + " " + product;
        OperationLog operationLog = new OperationLog(operation2,user,LocalDate.now(),LocalTime.now());
        session.save(operationLog);
        transaction.commit();
    }


    public void login(User user, String operation) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        OperationLog operationLog = new OperationLog(operation, user, LocalDate.now(),LocalTime.now());
        session.save(operationLog);
        transaction.commit();
    }


    public void purchaseLog(User user, double totalPrice, String operation) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String operation2 = operation + " " + String.valueOf(totalPrice);
        OperationLog operationLog = new OperationLog(operation2, user,LocalDate.now(),LocalTime.now());
        session.save(operationLog);
        transaction.commit();
    }

    public List<OperationLog> getAllOperations(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("select * from operation_log where user_id=?",OperationLog.class);
        nativeQuery.setParameter(1, id);
        List<OperationLog> list = nativeQuery.getResultList();
        transaction.commit();
        return list;
    }

}

