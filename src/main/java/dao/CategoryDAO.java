package dao;

import ConnectionFactory.*;
import entity.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by Artem Klots on 7/21/16.
 */
public class CategoryDAO {
    private Connection connection;
    private Statement statement;

    public List<Category> getAll() throws SQLException {
        List<Category> categories = new ArrayList<Category>();
        setUpConnection();

        ResultSet result = statement.executeQuery("SELECT * FROM categories ");
        while (result.next()) {
            categories.add(parse(result));
        }
        return categories;
    }

    public List<String> getAllCategoriesWithCountedProducts() throws SQLException {
        List<String> categories = new ArrayList<String>();
        setUpConnection();
        ResultSet result = statement.executeQuery("" +
                "SELECT categories.id, categories.title, COUNT(category_id) AS numbersOfItems " +
                "FROM items, categories " +
                "WHERE category_id = categories.id " +
                "GROUP BY categories.id");

        while (result.next()) {
            categories.add(parse(result).getTitle() + " " + result.getInt("numbersOfItems"));
        }
        return categories;
    }

    private void setUpConnection() throws SQLException {
        connection = new ConnectionFactoryImpl().getConnection();
        statement = connection.createStatement();
    }

    public Category parse(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String title = result.getString("title");
        return new Category(id, title);
    }
}
