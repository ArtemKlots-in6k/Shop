package dao;

import entity.Bill;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Artem Klots on 7/22/16.
 */
@Transactional
public class BillDAO {

    private SessionFactory sessionFactory;

    public BillDAO() {
    }

    public Bill create(Date date, User user)
            throws Exception {
            Bill bill = new Bill(date, user);
        sessionFactory.getCurrentSession().save(bill);
            return bill;
    }

    public List getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(Bill.class).list();
    }

    public Bill getBillById(int id) throws SQLException {
        Criteria billCriteria = sessionFactory.getCurrentSession().createCriteria(Bill.class);
        billCriteria.add(Restrictions.eq("id", id));
        return (Bill) billCriteria.uniqueResult();
    }

    public List getAllBillsByUserId(int id) throws SQLException {
        Query query = sessionFactory.getCurrentSession().createQuery("" +
                "select new entity.subsidiary.UserBill(purchase.bill, SUM (purchase.price)) " +
                "FROM Purchase purchase, in(purchase.bill) bill " +
                "WHERE bill.user.id = " + id + " " +
                "AND purchase.bill.id = bill.id " +
                "GROUP BY purchase.bill "
        );
        return query.list();
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
