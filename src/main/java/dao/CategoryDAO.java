package dao;

import entity.Category;
import entity.Item;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Artem Klots on 7/21/16.
 */
public class CategoryDAO extends HibernateDAO {

    public CategoryDAO() {
    }

    public Category getCategoryById(int id) throws SQLException {
        begin();
        Criteria categotiesCriteria = getSession().createCriteria(Category.class);
        categotiesCriteria.add(Restrictions.eq("id", id));
        commit();
        return (Category) categotiesCriteria.uniqueResult();
    }

    public Category getCategoryByTitle(String title) throws SQLException {
        begin();
        Criteria categotiesCriteria = getSession().createCriteria(Category.class);
        categotiesCriteria.add(Restrictions.eq("title", title));
        commit();
        return (Category) categotiesCriteria.uniqueResult();
    }

    public List getTopThreeItemsInCategory(String categoryTitle, Date today, Date twoMonthAgo) throws SQLException {
        Query query = getSession().createQuery("" +
                        "SELECT new entity.Top3(purchases.item,  COUNT(purchases.item.id)) " +
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
        try {
            begin();
            List result = getSession().createCriteria(Category.class).list();
            commit();
            return result;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get category ", e);
        }
    }

    public List getAllCategoriesWithCountedProducts() throws SQLException {
        Query query = getSession().createQuery("" +
                "SELECT new entity.CategoryStatistic(categories.id, categories.title,  COUNT(items.category)) " +
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
}
