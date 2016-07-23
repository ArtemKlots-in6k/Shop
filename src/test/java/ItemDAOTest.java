import dao.ItemDAO;
import entity.Category;
import entity.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 * Created by employee on 7/21/16.
 */
public class ItemDAOTest extends DatabaseInitializer {
    private ItemDAO itemDAO;

    @Before
    public void setUp() throws Exception {
        prepareConnection();
        super.setUp();
        itemDAO = new ItemDAO();
    }

    @After
    public void tearDown() throws Exception {
        prepareConnection();
        super.tearDown();
    }

    @Test
    public void gitItemById() throws Exception {
        assertThat(itemDAO.getItemById(1).getTitle(), is("Samsung"));
    }

    @Test
    public void getAll() throws Exception {
        List<Item> result = itemDAO.getAll();

        assertThat(result, contains(
                new Item("iPhone", new Category(0, "Phone"), new BigDecimal("700.00")),
                new Item("Samsung", new Category(0, "Phone"), new BigDecimal("300.50")),

                new Item("Lenovo Idea Pad", new Category(1, "Notebook"), new BigDecimal("1500.00")),

                new Item("iPad", new Category(2, "Tablets"), new BigDecimal("500.00")),
                new Item("iPad mini", new Category(2, "Tablets"), new BigDecimal("400.00")),
                new Item("Samsung and some text", new Category(2, "Tablets"), new BigDecimal("300.00"))
        ));
    }
}
