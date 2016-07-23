package dao;

import ConnectionFactory.ConnectionFactoryImpl;
import entity.Bill;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Artem Klots on 7/22/16.
 */
public class BillDAO extends DAO {

    public BillDAO() {
        tableName = "bills";
    }

    public Bill getBillById(int id) throws SQLException {
        setUpConnection();
        ResultSet result = statement.executeQuery("SELECT * FROM bills WHERE id = " + id);
        result.next();
        return parse(result);
    }

    public Map<Integer, Map<Integer, Date>> getAllBillsByUserId(int id) throws SQLException {
        Map<Integer, Map<Integer, Date>> result = new LinkedHashMap<>();
        setUpConnection();

        ResultSet response = statement.executeQuery("SELECT bills.id, date, SUM(purchases.price) AS amount " +
                "FROM bills " +
                "LEFT JOIN purchases on bill_id = bills.id " +
                "WHERE user_id = '" + id + "' " +
                "GROUP BY bills.id ");
        while (response.next()) {
            Map<Integer, Date> bill = new LinkedHashMap<>();
            bill.put(response.getInt("amount"), response.getDate("date"));
            result.put(response.getInt("id"), bill);
        }
        return result;
    }

    public Bill parse(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        LocalDate date = result.getDate("date").toLocalDate();
        int userId = result.getInt("user_id");
        return new Bill(date, new UserDao().getUserById(userId));
    }
}
