package dao;

import ConnectionFactory.ConnectionFactoryImpl;
import entity.Item;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem Klots on 7/21/16.
 */
public class ItemDAO extends DAO {

    public ItemDAO() {
        tableName = "items";
    }

    public Item getItemById(int id) throws SQLException {
        setUpConnection();
        ResultSet result = statement.executeQuery("SELECT * FROM items WHERE id = " + id);
        result.next();
        return parse(result);
    }

    public Item parse(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String title = result.getString("title");
        int categoryId = result.getInt("category_id");
        BigDecimal price = result.getBigDecimal("price");
        return new Item(id, title, new CategoryDAO().getCategoryById(categoryId), price);
    }
}
