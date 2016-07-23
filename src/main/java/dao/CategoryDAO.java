package dao;

import ConnectionFactory.*;
import entity.Category;
import entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by Artem Klots on 7/21/16.
 */
public class CategoryDAO {
    private Statement statement;

    public Category getCategoryById(int id) throws SQLException {
        setUpConnection();
        ResultSet result = statement.executeQuery("SELECT * FROM categories WHERE id = " + id + ";");
        result.next();
        return parse(result);
    }

    public Category getCategoryByTitle(String title) throws SQLException {
        setUpConnection();
        ResultSet result = statement.executeQuery("SELECT * FROM categories WHERE title = '" + title + "';");
        result.next();
        return parse(result);
    }

    public Map<Item, Integer> getTopThreeItemsInCategory(String category) throws SQLException {
        Map<Item, Integer> result = new LinkedHashMap<>();
        setUpConnection();

        ResultSet response = statement.executeQuery("" +
                "SELECT items.id, COUNT(category_id) AS numberOfSales " +
                "FROM items " +
                "LEFT JOIN purchases ON item_id = items.id " +
                "LEFT JOIN bills ON bill_id = bills.id " +
                "WHERE category_id = " + new CategoryDAO().getCategoryByTitle(category).getId() + " " +
                "AND date >= DATE_SUB(CURRENT_DATE, INTERVAL 60 DAY) " +
                "GROUP BY items.id " +
                "ORDER BY COUNT(item_id) DESC " +
                "LIMIT 3");
        while (response.next()) {
            result.put(new ItemDAO().getItemById(response.getInt("id")), response.getInt("numberOfSales"));
        }
        return result;
    }

    public List<Category> getAll() throws SQLException {
        List<Category> categories = new ArrayList<Category>();
        setUpConnection();

        ResultSet result = statement.executeQuery("SELECT * FROM categories ");
        while (result.next()) {
            categories.add(parse(result));
        }
        return categories;
    }

    public Map<Category, Integer> getAllCategoriesWithCountedProducts() throws SQLException {
        Map<Category, Integer> categories = new LinkedHashMap<>();
        setUpConnection();
        ResultSet result = statement.executeQuery("" +
                "SELECT categories.id, categories.title, COUNT(category_id) AS numberOfItems " +
                "FROM items, categories " +
                "WHERE category_id = categories.id " +
                "GROUP BY categories.id");

        while (result.next()) {
            int categoryId = result.getInt("id");
            int numberOfItems = result.getInt("id");
            categories.put(new CategoryDAO().getCategoryById(categoryId), numberOfItems);
        }
        return categories;
    }

    private void setUpConnection() throws SQLException {
        Connection connection = new ConnectionFactoryImpl().getConnection();
        statement = connection.createStatement();
    }

    public Category parse(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String title = result.getString("title");
        return new Category(id, title);
    }
}
