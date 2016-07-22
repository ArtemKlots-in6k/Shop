package dao;

import ConnectionFactory.ConnectionFactoryImpl;
import entity.Bill;
import entity.Purchase;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem Klots on 7/22/16.
 */
public class PurchaseDAO {
    private Statement statement;

    public List<Purchase> getAll() throws SQLException {
        List<Purchase> purchases = new ArrayList<Purchase>();
        setUpConnection();

        ResultSet result = statement.executeQuery("SELECT * FROM purchases");
        while (result.next()) {
            purchases.add(parse(result));
        }
        return purchases;
    }

    private void setUpConnection() throws SQLException {
        Connection connection = new ConnectionFactoryImpl().getConnection();
        statement = connection.createStatement();
    }

    public Purchase parse(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        int itemId = result.getInt("item_id");
        BigDecimal price = result.getBigDecimal("price");
        int billId = result.getInt("bill_id");
        return new Purchase(id, itemId, price, new BillDAO().getBillById(id));
    }
}
