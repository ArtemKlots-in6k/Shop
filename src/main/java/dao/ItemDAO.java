package dao;

import entity.Category;
import entity.Item;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.*;
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
        return sessionFactory.getCurrentSession().createCriteria(Item.class).list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
