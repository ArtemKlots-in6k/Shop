import dao.CategoryDAO;
import dao.ItemDAO;
import entity.Category;
import entity.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 * Created by employee on 7/21/16.
 */
public class ItemDAOTest extends DatabaseInitializer {
    private ItemDAO itemDAO;
    private Category phones = new Category(0, "Phone");
    private Category notebooks = new Category(1, "Notebook");
    private Category tablets = new Category(2, "Tablets");
    private List<Item> expected;

    @Before
    public void setUp() throws Exception {
        prepareConnection();
        super.setUp();
        itemDAO = new ItemDAO();
        expected = new ArrayList<>(asList(
                new Item("iPhone", phones, new BigDecimal("700.00")),
                new Item("Samsung", phones, new BigDecimal("300.50")),
                new Item("Lenovo", phones, new BigDecimal("400.50")),
                new Item("Lenovo Idea Pad", notebooks, new BigDecimal("1500.00")),
                new Item("iPad", tablets, new BigDecimal("500.00")),
                new Item("iPad mini", tablets, new BigDecimal("400.00")),
                new Item("Samsung and some text", tablets, new BigDecimal("300.00"))
        ));
    }

    @After
    public void tearDown() throws Exception {
        prepareConnection();
        super.tearDown();
    }

    @Test
    public void getItemById() throws Exception {
        Item expectedItem = new Item(1, "Samsung", new Category(0, "Phone"), new BigDecimal("300.50"));
        assertThat(itemDAO.getItemById(1), is(expectedItem));
    }

    @Test
    public void getItemByAnotherId() throws Exception {
        Item expectedItem = new Item(2, "Lenovo", new Category(0, "Phone"), new BigDecimal("400.50"));
        assertThat(itemDAO.getItemById(2), is(expectedItem));
    }

    @Test
    public void getAll() throws Exception {
        assertThat(itemDAO.getAll(), is(expected));
    }

    @Test
    public void createAndGetAll() throws Exception {
        expected.add(new Item("New Phone", phones, new BigDecimal("550.00")));

        itemDAO.create("New Phone", new CategoryDAO().getCategoryById(0), new BigDecimal("550.00"));

        assertThat(itemDAO.getAll(), is(expected));
    }
}
