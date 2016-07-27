package dao;

import ConnectionFactory.ConnectionFactoryImpl;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Artem Klots on 7/22/16.
 */
@Transactional
@Repository
public class UserDao {

    private SessionFactory sessionFactory;

    public User create(String username)
            throws Exception {
            User user = new User(username);
        sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().save(user);
            return user;
    }

    public User getUserByName(String username) throws Exception {
        Query q = sessionFactory.getCurrentSession().createQuery("from User where name = :username");
            q.setString("username", username);
            User user = (User) q.uniqueResult();
            return user;
    }

    public User getUserById(int id) throws Exception {
        Criteria userCriteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        userCriteria.add(Restrictions.eq("id", id));
        return (User) userCriteria.uniqueResult();
    }

    public List getAll() throws Exception {
        List result = sessionFactory.getCurrentSession().createCriteria(User.class).list();
            return result;
    }

    public User parse(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name = result.getString("name");
        return new User(id, name);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
