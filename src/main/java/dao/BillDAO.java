package dao;

import ConnectionFactory.ConnectionFactoryImpl;
import entity.Bill;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem Klots on 7/22/16.
 */
public class BillDAO {
    private Statement statement;

    public Bill getBillById(int id) throws SQLException {
        setUpConnection();
        ResultSet result = statement.executeQuery("SELECT * FROM bills WHERE id = " + id);
        result.next();
        return parse(result);
    }

    public List<Bill> getAll() throws SQLException {
        List<Bill> bills = new ArrayList<Bill>();
        setUpConnection();

        ResultSet result = statement.executeQuery("SELECT * FROM bills");
        while (result.next()) {
            bills.add(parse(result));
        }
        return bills;
    }

    private void setUpConnection() throws SQLException {
        Connection connection = new ConnectionFactoryImpl().getConnection();
        statement = connection.createStatement();
    }

    public Bill parse(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        Date date = result.getDate("date");
        return new Bill(date, new UserDao().getUserById(id));
    }
}
