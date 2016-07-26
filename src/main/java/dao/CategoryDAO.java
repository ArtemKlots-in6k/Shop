package dao;

import entity.Category;
import entity.Item;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    public List getTopThreeItemsInCategory(String categoryTitle) throws SQLException {
        Query query = getSession().createQuery("" +
                        "SELECT new entity.Top3(items,  COUNT(items.category)) " +
                        "FROM Item items, Category categories " +
                        "WHERE categories.title = '" + categoryTitle + "' " +
                        "GROUP BY items.id "
//                "ORDER BY COUNT(items.category.id) DESC "
        ).setMaxResults(3);
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
