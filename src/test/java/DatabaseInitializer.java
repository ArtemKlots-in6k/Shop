import ConnectionFactory.ConnectionFactoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by employee on 7/22/16.
 */
public class DatabaseInitializer {
    Connection connection;
    Statement statement;

    protected void setUp() throws Exception {
        prepareTables();
        prepareCategory();
        prepareItem();
    }

    protected void tearDown() throws Exception {
        statement.executeUpdate("DROP TABLE items");
        statement.executeUpdate("DROP TABLE categories");
    }

    protected void prepareConnection() throws SQLException {
        connection = new ConnectionFactoryImpl().getConnection();
        statement = connection.createStatement();
    }

    private void prepareTables() throws SQLException {
        statement.executeUpdate("CREATE TABLE categories (" +
                "id INT IDENTITY PRIMARY KEY NOT NULL," +
                "title VARCHAR(25) NOT NULL)");

        statement.executeUpdate("CREATE TABLE items (" +
                "id INT IDENTITY PRIMARY KEY NOT NULL," +
                "title VARCHAR(25) NOT NULL," +
                "category_id INT, " +
                "price DECIMAL(7,2))");

        statement.executeUpdate("ALTER TABLE items " +
                "ADD FOREIGN KEY(category_id) " +
                "REFERENCES categories(id)");
    }

    private void prepareCategory() throws SQLException {
        statement.executeUpdate("INSERT INTO categories (title) VALUES ('Phone')");
        statement.executeUpdate("INSERT INTO categories (title) VALUES ('Notebook')");
        statement.executeUpdate("INSERT INTO categories (title) VALUES ('Tablets')");
    }

    private void prepareItem() throws SQLException {

        statement.executeUpdate("INSERT INTO items (title, category_id, price) VALUES ('iPhone', 0, 700.00)");
        statement.executeUpdate("INSERT INTO items (title, category_id, price) VALUES ('Samsung', 0, 300.50)");

        statement.executeUpdate("INSERT INTO items (title, category_id, price) VALUES ('Lenovo Idea Pad', 1, 1500.00)");

        statement.executeUpdate("INSERT INTO items (title, category_id, price) VALUES ('iPad', 2, 500.00)");
        statement.executeUpdate("INSERT INTO items (title, category_id, price) VALUES ('iPad mini', 2, 400.00)");
        statement.executeUpdate("INSERT INTO items (title, category_id, price) VALUES ('Samsung and some text', 2, 300.00)");
    }
}
