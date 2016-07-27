package dao;

import ConnectionFactory.ConnectionFactoryImpl;
import entity.Bill;
import entity.Category;
import entity.Item;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem Klots on 7/21/16.
 */
@Transactional
@Repository
public class ItemDAO {

    private SessionFactory sessionFactory;

    @Autowired
    CategoryDAO categoryDAO;

    public ItemDAO() {

    }

    // TODO: 7/26/16 Не работает
    public Item create(String title, Category category, BigDecimal price)
            throws Exception {
            Item item = new Item(title, category, price);
        sessionFactory.getCurrentSession().save(item);
            return item;
    }

    public Item getItemById(int id) throws SQLException {
        Criteria itemCriteria = sessionFactory.getCurrentSession().createCriteria(Item.class);
        itemCriteria.add(Restrictions.eq("id", id));
        return (Item) itemCriteria.uniqueResult();
    }

    public List getAll() throws Exception {
        List result = sessionFactory.getCurrentSession().createCriteria(Item.class).list();
            return result;
    }

    public Item parse(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String title = result.getString("title");
        int categoryId = result.getInt("category_id");
        BigDecimal price = result.getBigDecimal("price");
        return new Item(id, title, categoryDAO.getCategoryById(categoryId), price);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
