package dao;

import ConnectionFactory.ConnectionFactoryImpl;
import entity.Bill;
import entity.Item;
import entity.Purchase;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem Klots on 7/22/16.
 */
@Transactional
@Repository
public class PurchaseDAO {

    private SessionFactory sessionFactory;

    public PurchaseDAO() {
    }

    public Purchase create(Item item, BigDecimal price, Bill bill) throws Exception {
            Purchase purchase = new Purchase(item, price, bill);
        sessionFactory.getCurrentSession().save(purchase);
            return purchase;
    }

    public Purchase getPurchaseById(int id) throws SQLException {
        Criteria purchaseCriteria = sessionFactory.getCurrentSession().createCriteria(Purchase.class);
        purchaseCriteria.add(Restrictions.eq("id", id));
        return (Purchase) purchaseCriteria.uniqueResult();
    }

    public List<Purchase> getAllPurchasesByBillId(int id) throws SQLException {
//        setUpConnection();
        List<Purchase> purchases = new ArrayList<>();
//        ResultSet result = statement.executeQuery("SELECT * FROM " + tableName + " WHERE bill_id = " + id);
//        while (result.next()) {
//            purchases.add(parse(result));
//        }
//        return purchases;
        Query query = sessionFactory.getCurrentSession().createQuery(
                "FROM Purchase purchases " +
                        "WHERE purchases.bill.id = " + id);
        return query.list();
    }

    public Purchase parse(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        int itemId = result.getInt("item_id");
        BigDecimal price = result.getBigDecimal("price");
        int billId = result.getInt("bill_id");
        return new Purchase(id, new ItemDAO().getItemById(itemId), price, new BillDAO().getBillById(billId));
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
