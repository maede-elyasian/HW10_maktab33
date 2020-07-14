package dao;

import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.userService.UserValidationService;

import java.util.List;

@Component
public class UserDao {
@Autowired
    private SessionFactory sessionFactory = MySessionFactory.getSessionFactory();

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
        if (username != null && password != null) {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            Criterion c1 = Restrictions.eq("userName", username);
            Criterion c2 = Restrictions.eq("password", password);
            Disjunction conjunction = Restrictions.disjunction(c1, c2);
            criteria.add(conjunction);
            User user = (User) criteria.uniqueResult();
            transaction.commit();
            return user;
        }
        return null;
    }
}
