package dao;

import ConnectionFactory.ConnectionFactoryImpl;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static dao.HibernateDAO.getSession;

/**
 * Created by Artem Klots on 7/22/16.
 */
public class UserDao extends HibernateDAO {

    public User create(String username)
            throws Exception {
        try {
            begin();
            User user = new User(username);
            getSession().save(user);
            commit();
            return user;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not create user " + username + e.getMessage(), e);
        }
    }

    public User getUserByName(String username) throws Exception {
        try {
            begin();
            Query q = getSession().createQuery("from User where name = :username");
            q.setString("username", username);
            User user = (User) q.uniqueResult();
            commit();
            return user;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get user " + username, e);
        }
    }

    public User getUserById(int id) throws Exception {
        begin();
        Criteria userCriteria = getSession().createCriteria(User.class);
        userCriteria.add(Restrictions.eq("id", id));
        commit();
        return (User) userCriteria.uniqueResult();
    }

    public List getAll() throws Exception {
        try {
            begin();
            List result = getSession().createCriteria(User.class).list();
            commit();
            return result;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get user ", e);
        }
    }

    public User parse(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name = result.getString("name");
        return new User(id, name);
    }
}
