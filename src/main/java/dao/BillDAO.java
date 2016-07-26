package dao;

import ConnectionFactory.ConnectionFactoryImpl;
import entity.Bill;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Artem Klots on 7/22/16.
 */
public class BillDAO extends HibernateDAO {

    public BillDAO() {
    }

    public Bill create(Date date, User user)
            throws Exception {
        try {
            begin();
            Bill bill = new Bill(date, user);
            getSession().save(bill);
            commit();
            return bill;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not create bill" + e.getMessage(), e);
        }
    }

    public List getAll() throws Exception {
        try {
            begin();
            List result = getSession().createCriteria(Bill.class).list();
            commit();
            return result;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get bills ", e);
        }
    }

    public Bill getBillById(int id) throws SQLException {
        begin();
        Criteria billCriteria = getSession().createCriteria(Bill.class);
        billCriteria.add(Restrictions.eq("id", id));
        commit();
        return (Bill) billCriteria.uniqueResult();
    }

    public Map<Integer, Map<Integer, Date>> getAllBillsByUserId(int id) throws SQLException {
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
        return result;
    }

    public Bill parse(ResultSet result) throws Exception {
        int id = result.getInt("id");
        Date date = result.getDate("date");
        int userId = result.getInt("user_id");
//        return new Bill(date, new UserDao().getUserById(userId)); // TODO: 7/25/16 починить
        return new Bill(date, new UserDao().getUserById(userId));
    }
}
