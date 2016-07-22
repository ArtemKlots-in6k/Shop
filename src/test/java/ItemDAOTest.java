import ConnectionFactory.ConnectionFactoryImpl;
import dao.ItemDAO;
import entity.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 * Created by employee on 7/21/16.
 */
public class ItemDAOTest extends DatabaseInitializer {

    @Before
    public void setUp() throws Exception {
        prepareConnection();
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        prepareConnection();
        super.tearDown();
    }

    @Test
    public void getAll() throws Exception {
        ItemDAO itemDAO = new ItemDAO();

        List<Item> result = itemDAO.getAll();

        assertThat(result, contains(
                new Item("iPhone", 0, new BigDecimal("700.00")),
                new Item("Samsung", 0, new BigDecimal("300.50")),

                new Item("Lenovo Idea Pad", 1, new BigDecimal("1500.00")),

                new Item("iPad", 2, new BigDecimal("500.00")),
                new Item("iPad mini", 2, new BigDecimal("400.00")),
                new Item("Samsung and some text", 2, new BigDecimal("300.00"))
        ));
    }
}
