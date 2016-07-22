package dao;

import ConnectionFactory.ConnectionFactoryImpl;
import entity.Item;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem Klots on 7/21/16.
 */
public class ItemDAO {

    public List<Item> getAll() throws SQLException {
        List<Item> items = new ArrayList<Item>();

        Connection connection = new ConnectionFactoryImpl().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM items ");
        while (result.next()) {
            int id = result.getInt("id");
            String title = result.getString("title");
            int categoryId = result.getInt("category_id");
            BigDecimal price = result.getBigDecimal("price");
            items.add(new Item(id, title, categoryId, price));
        }
        return items;
    }
}
