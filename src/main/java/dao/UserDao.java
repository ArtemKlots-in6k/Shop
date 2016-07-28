package dao;

import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Artem Klots on 7/22/16.
 */
@Transactional
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
        return (User) q.uniqueResult();
    }

    public User getUserById(int id) throws Exception {
        Criteria userCriteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        userCriteria.add(Restrictions.eq("id", id));
        return (User) userCriteria.uniqueResult();
    }

    public List getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
