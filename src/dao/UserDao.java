package dao;

import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import service.userService.validation.UserValidationService;

import java.util.List;

@Component
@Lazy
@Scope("prototype")
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        transaction.commit();
        return user;
    }

    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List users = session.createQuery("from User", User.class).list();
        transaction.commit();
        return users;
    }

    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        if (UserValidationService.isValidUser(user)) {
            session.save(user);
            transaction.commit();
        }
    }

    public void deleteUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("delete from User where id=:id", User.class);
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
    }

    public User searchUser(String username, String password) {
        User user = null;
        if (username != null && password != null) {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from User u where u.userName=:username and u.password=:password", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            user = (User) query.uniqueResult();

            if (user != null) {
                transaction.commit();
                return user;

            }
        }
        return null;
    }
}
