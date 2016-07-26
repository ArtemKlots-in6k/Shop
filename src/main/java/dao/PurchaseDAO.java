package dao;

import ConnectionFactory.ConnectionFactoryImpl;
import entity.Bill;
import entity.Item;
import entity.Purchase;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem Klots on 7/22/16.
 */
public class PurchaseDAO extends HibernateDAO {

    public PurchaseDAO() {
    }

    public Purchase create(Item item, BigDecimal price, Bill bill)
            throws Exception {
        try {
            begin();
            Purchase purchase = new Purchase(item, price, bill);
            getSession().save(purchase);
            commit();
            return purchase;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not create purchase" + e.getMessage(), e);
        }
    }

    public Purchase getPurchaseById(int id) throws SQLException {
        begin();
        Criteria purchaseCriteria = getSession().createCriteria(Purchase.class);
        purchaseCriteria.add(Restrictions.eq("id", id));
        commit();
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
        Query query = getSession().createQuery(
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
}
