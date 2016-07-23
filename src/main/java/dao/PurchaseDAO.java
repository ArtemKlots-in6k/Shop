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
public class PurchaseDAO extends DAO {

    public PurchaseDAO() {
        tableName = "purchases";
    }

    public Purchase getPurchaseById(int id) throws SQLException {
        setUpConnection();
        ResultSet result = statement.executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id + ";");
        result.next();
        return parse(result);
    }

    public List<Purchase> getAllPurchasesByBillId(int id) throws SQLException {
        setUpConnection();
        List<Purchase> purchases = new ArrayList<>();
        ResultSet result = statement.executeQuery("SELECT * FROM " + tableName + " WHERE bill_id = " + id);
        while (result.next()) {
            purchases.add(parse(result));
        }
        return purchases;
    }

    public Purchase parse(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        int itemId = result.getInt("item_id");
        BigDecimal price = result.getBigDecimal("price");
        int billId = result.getInt("bill_id");
        return new Purchase(id, new ItemDAO().getItemById(itemId), price, new BillDAO().getBillById(billId));
    }
}
