package dao;

import ConnectionFactory.ConnectionFactoryImpl;
import entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem Klots on 7/22/16.
 */
public class UserDao extends DAO {

    public UserDao() {
        tableName = "users";
    }

    public User getUserById(int id) throws SQLException {
        setUpConnection();
        ResultSet result = statement.executeQuery("SELECT * FROM users WHERE id = " + id + ";");
        result.next();
        return parse(result);
    }

    public User parse(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name = result.getString("name");
        return new User(id, name);
    }
}
