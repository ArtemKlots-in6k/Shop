package dao;

import entity.Category;
import entity.Item;
import org.hibernate.Criteria;
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

    public Map<Item, Integer> getTopThreeItemsInCategory(String category) throws SQLException {
        Map<Item, Integer> result = new LinkedHashMap<>();
//        setUpConnection();
//
//        ResultSet response = statement.executeQuery("" +
//                "SELECT items.id, COUNT(category_id) AS numberOfSales " +
//                "FROM items " +
//                "LEFT JOIN purchases ON item_id = items.id " +
//                "LEFT JOIN bills ON bill_id = bills.id " +
//                "WHERE category_id = " + new CategoryDAO().getCategoryByTitle(category).getId() + " " +
//                "AND date >= DATE_SUB(CURRENT_DATE, INTERVAL 60 DAY) " +
//                "GROUP BY items.id " +
//                "ORDER BY COUNT(item_id) DESC " +
//                "LIMIT 3");
//        while (response.next()) {
//            result.put(new ItemDAO().getItemById(response.getInt("id")), response.getInt("numberOfSales"));
//        }
        return result;
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
