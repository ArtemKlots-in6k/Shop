import ConnectionFactory.ConnectionFactoryImpl;
import dao.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Artem Klots on 7/22/16.
 */
public class DatabaseInitializer {
    private Statement statement;
    private ClassPathXmlApplicationContext applicationContext;
    BillDAO billDAO;
    CategoryDAO categoryDAO;
    ItemDAO itemDAO;
    PurchaseDAO purchaseDAO;
    UserDao userDao;

    protected void setUp() throws Exception {
        prepareTables();
        prepareCategories();
        prepareItems();
        preparePurchases();
        prepareBills();
        prepareUsers();
        beansSetUp();
    }

    private void beansSetUp() {
        applicationContext =
                new ClassPathXmlApplicationContext(new String[]{"config.xml"});
        billDAO = (BillDAO) applicationContext.getBean("billDAO");
        categoryDAO = (CategoryDAO) applicationContext.getBean("categoryDAO");
        itemDAO = (ItemDAO) applicationContext.getBean("itemDAO");
        purchaseDAO = (PurchaseDAO) applicationContext.getBean("purchaseDAO");
        userDao = (UserDao) applicationContext.getBean("userDAO");
    }

    protected void tearDown() throws Exception {
        statement.executeUpdate("DROP TABLE items");
        statement.executeUpdate("DROP TABLE categories");
        statement.executeUpdate("DROP TABLE purchases");
        statement.executeUpdate("DROP TABLE bills");
        statement.executeUpdate("DROP TABLE users");
    }

    protected void prepareConnection() throws SQLException {
        Connection connection = new ConnectionFactoryImpl().getConnection();
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

        statement.executeUpdate("CREATE TABLE users (" +
                "id INT IDENTITY PRIMARY KEY NOT NULL," +
                "name VARCHAR(25) NOT NULL)");


        statement.executeUpdate("CREATE TABLE bills (" +
                "id INT IDENTITY PRIMARY KEY NOT NULL," +
                "date DATETIME NOT NULL," +
                "user_id INT NOT NULL)");

        statement.executeUpdate("CREATE TABLE purchases (" +
                "id INT IDENTITY PRIMARY KEY NOT NULL," +
                "item_id INT NOT NULL," +
                "price DECIMAL NOT NULL," +
                "bill_id INT NOT NULL)");
    }

    private void prepareCategories() throws SQLException {
        statement.executeUpdate("INSERT INTO categories (title) VALUES ('Phone')");
        statement.executeUpdate("INSERT INTO categories (title) VALUES ('Notebook')");
        statement.executeUpdate("INSERT INTO categories (title) VALUES ('Tablets')");
    }

    private void prepareItems() throws SQLException {

        statement.executeUpdate("INSERT INTO items (title, category_id, price) VALUES ('iPhone', 0, 700.00)");
        statement.executeUpdate("INSERT INTO items (title, category_id, price) VALUES ('Samsung', 0, 300.50)");
        statement.executeUpdate("INSERT INTO items (title, category_id, price) VALUES ('Lenovo', 0, 400.50)");

        statement.executeUpdate("INSERT INTO items (title, category_id, price) VALUES ('Lenovo Idea Pad', 1, 1500.00)");

        statement.executeUpdate("INSERT INTO items (title, category_id, price) VALUES ('iPad', 2, 500.00)");
        statement.executeUpdate("INSERT INTO items (title, category_id, price) VALUES ('iPad mini', 2, 400.00)");
        statement.executeUpdate("INSERT INTO items (title, category_id, price) VALUES ('Samsung and some text', 2, 300.00)");
    }

    private void prepareUsers() throws SQLException {
        statement.executeUpdate("INSERT INTO users (name) VALUES ('Robert')");
        statement.executeUpdate("INSERT INTO users (name) VALUES ('John')");
    }

    private void prepareBills() throws SQLException {
        statement.executeUpdate("INSERT INTO bills (date, user_id) VALUES ('2016-07-18 12:30:15', 1)");
        statement.executeUpdate("INSERT INTO bills (date, user_id) VALUES ('2016-07-18 11:30:15', 0)");
        statement.executeUpdate("INSERT INTO bills (date, user_id) VALUES ('2016-05-18 11:30:15', 0)");
    }

    private void preparePurchases() throws SQLException {
        statement.executeUpdate("INSERT INTO purchases (item_id, price, bill_id) VALUES ( 0, 750.00, 0)");
        statement.executeUpdate("INSERT INTO purchases (item_id, price, bill_id) VALUES ( 1, 350.00, 0)");
        statement.executeUpdate("INSERT INTO purchases (item_id, price, bill_id) VALUES ( 2, 1300.00, 0)");
        statement.executeUpdate("INSERT INTO purchases (item_id, price, bill_id) VALUES ( 2, 1300.00, 2)");
        statement.executeUpdate("INSERT INTO purchases (item_id, price, bill_id) VALUES ( 2, 1300.00, 2)");
        statement.executeUpdate("INSERT INTO purchases (item_id, price, bill_id) VALUES ( 0, 700.00, 1)");
        statement.executeUpdate("INSERT INTO purchases (item_id, price, bill_id) VALUES ( 0, 700.00, 1)");
        statement.executeUpdate("INSERT INTO purchases (item_id, price, bill_id) VALUES ( 1, 400.00, 0)");

    }
}
