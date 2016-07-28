package dao;

import entity.Bill;
import entity.Item;
import entity.Purchase;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.*;
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
        Query query = sessionFactory.getCurrentSession().createQuery(
                "FROM Purchase purchases " +
                        "WHERE purchases.bill.id = " + id);
        return query.list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
