package dao;

import ConnectionFactory.ConnectionFactoryImpl;
import entity.Bill;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Artem Klots on 7/22/16.
 */
@Transactional
@Repository
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
        List result = sessionFactory.getCurrentSession().createCriteria(Bill.class).list();
            return result;
    }

    public Bill getBillById(int id) throws SQLException {
        Criteria billCriteria = sessionFactory.getCurrentSession().createCriteria(Bill.class);
        billCriteria.add(Restrictions.eq("id", id));
        return (Bill) billCriteria.uniqueResult();
    }

    public List getAllBillsByUserId(int id) throws SQLException {
        Map<Integer, Map<Integer, Date>> result = new LinkedHashMap<>();
//        setUpConnection();
//
//        ResultSet response = statement.executeQuery("SELECT bills.id, date, SUM(purchases.price) AS amount " +
//                "FROM bills " +
//                "LEFT JOIN purchases on bill_id = bills.id " +
//                "WHERE user_id = '" + id + "' " +
//                "GROUP BY bills.id ");
//        while (response.next()) {
//            Map<Integer, Date> bill = new LinkedHashMap<>();
//            bill.put(response.getInt("amount"), response.getDate("date"));
//            result.put(response.getInt("id"), bill);
//        }
//        return result;

        Query query = sessionFactory.getCurrentSession().createQuery("" +
                "select new entity.subsidiary.UserBill(purchase.bill, SUM (purchase.price)) " +
                "FROM Purchase purchase, in(purchase.bill) bill " +
                "WHERE bill.user.id = " + id + " " +
                "AND purchase.bill.id = bill.id " +
                "GROUP BY purchase.bill "
        );
        return query.list();
    }

    public Bill parse(ResultSet result) throws Exception {
        int id = result.getInt("id");
        Date date = result.getDate("date");
        int userId = result.getInt("user_id");
//        return new Bill(date, new UserDao().getUserById(userId)); // TODO: 7/25/16 починить
        return new Bill(date, new UserDao().getUserById(userId));
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
