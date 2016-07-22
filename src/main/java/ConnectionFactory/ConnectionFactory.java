package ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Artem Klots on 7/21/16.
 */
public interface ConnectionFactory {
    Connection getConnection() throws SQLException;
}
