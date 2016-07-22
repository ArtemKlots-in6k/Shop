package ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Artem Klots on 7/21/16.
 */
public class ConnectionFactoryImpl implements ConnectionFactory {
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:mem:shop", "user", "852");
    }
}
