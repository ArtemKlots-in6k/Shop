package dao;

import ConnectionFactory.ConnectionFactoryImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem Klots on 23.07.2016.
 */
public abstract class DAO {
    protected Statement statement;
    String tableName;

    abstract Object parse(ResultSet result) throws SQLException;

    public List getAll() throws SQLException {
        List allRecords = new ArrayList();
        setUpConnection();

        ResultSet result = statement.executeQuery("SELECT * FROM " + tableName);
        while (result.next()) {
            allRecords.add(parse(result));
        }
        return allRecords;
    }

    protected void setUpConnection() throws SQLException {
        Connection connection = new ConnectionFactoryImpl().getConnection();
        statement = connection.createStatement();
    }

}
