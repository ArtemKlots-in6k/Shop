package dao;

/**
 * Created by Artem Klots on 24.07.2016.
 */

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateDAO {
    private static final Logger log = Logger.getAnonymousLogger();
    private static final ThreadLocal session = new ThreadLocal();
    private static final SessionFactory sessionFactory =
            new AnnotationConfiguration().configure().buildSessionFactory();

    protected HibernateDAO() {
    }

    public static Session getSession() {
        Session session = (Session) HibernateDAO.session.get();
        if (session == null) {
            session = sessionFactory.openSession();
            HibernateDAO.session.set(session);
        }
        return session;
    }

    protected void begin() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }

    protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot rollback", e);
        }
        try {
            getSession().close();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot close", e);
        }
        HibernateDAO.session.set(null);
    }

    public static void close() {
        getSession().close();
        HibernateDAO.session.set(null);
    }
}