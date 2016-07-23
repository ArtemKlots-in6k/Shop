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

        assertThat(result.size(), is(7));
    }
}
