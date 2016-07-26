package dao;

import ConnectionFactory.ConnectionFactoryImpl;
import entity.Bill;
import entity.Category;
import entity.Item;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem Klots on 7/21/16.
 */
public class ItemDAO extends HibernateDAO {

    public ItemDAO() {

    }

    // TODO: 7/26/16 Не работает
    public Item create(String title, Category category, BigDecimal price)
            throws Exception {
        try {
            begin();
            Item item = new Item(title, category, price);
            getSession().save(item);
            commit();
            return item;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not create item" + e.getMessage(), e);
        }
    }

    public Item getItemById(int id) throws SQLException {
        begin();
        Criteria itemCriteria = getSession().createCriteria(Item.class);
        itemCriteria.add(Restrictions.eq("id", id));
        commit();
        return (Item) itemCriteria.uniqueResult();
    }

    public List getAll() throws Exception {
        try {
            begin();
            List result = getSession().createCriteria(Item.class).list();
            commit();
            return result;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get items ", e);
        }
    }

    public Item parse(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String title = result.getString("title");
        int categoryId = result.getInt("category_id");
        BigDecimal price = result.getBigDecimal("price");
        return new Item(id, title, new CategoryDAO().getCategoryById(categoryId), price);
    }
}
