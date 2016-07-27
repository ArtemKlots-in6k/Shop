package dao;

import entity.Category;
import entity.Item;
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
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Artem Klots on 7/21/16.
 */
@Transactional
@Repository
public class CategoryDAO {

    private SessionFactory sessionFactory;

    public Category getCategoryById(int id) throws SQLException {
        Criteria categoriesCriteria = sessionFactory.getCurrentSession().createCriteria(Category.class);
        categoriesCriteria.add(Restrictions.eq("id", id));
        return (Category) categoriesCriteria.uniqueResult();
    }

    public Category getCategoryByTitle(String title) throws SQLException {
        Criteria categoriesCriteria = sessionFactory.getCurrentSession().createCriteria(Category.class);
        categoriesCriteria.add(Restrictions.eq("title", title));
        return (Category) categoriesCriteria.uniqueResult();
    }

    public List getTopThreeItemsInCategory(String categoryTitle, Date today, Date twoMonthAgo) throws SQLException {
        Query query = sessionFactory.getCurrentSession().createQuery("" +
                        "SELECT new entity.subsidiary.Top3(purchases.item,  COUNT(purchases.item.id)) " +
                        "FROM Purchase purchases " +
                        "WHERE purchases.item.category.title = :categoryTitle " +
                        "AND purchases.bill.date between :twoMonthAgo AND :today  " +
                        "GROUP BY purchases.item " +
                        "ORDER BY COUNT(purchases.item.id) DESC "
//                "ORDER BY COUNT(items.category.id) DESC "
        ).setParameter("categoryTitle", categoryTitle)
                .setParameter("today", today)
                .setParameter("twoMonthAgo", twoMonthAgo)
                .setMaxResults(3);
        return query.list();
//        return result;
    }

    public List getAll() throws Exception {
        List result = sessionFactory.getCurrentSession().createCriteria(Category.class).list();
            return result;
    }

    public List getAllCategoriesWithCountedProducts() throws SQLException {
        Query query = sessionFactory.getCurrentSession().createQuery("" +
                "SELECT new entity.subsidiary.CategoryStatistic(categories.id, categories.title,  COUNT(items.category)) " +
                "FROM Category categories, Item items " +
                "WHERE categories.id = items.category.id " +
                "GROUP BY categories.id");
        return query.list();
    }

    public Category parse(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String title = result.getString("title");
        return new Category(id, title);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
