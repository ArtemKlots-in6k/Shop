import dao.CategoryDAO;
import entity.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.util.*;

/**
 * Created by employee on 7/21/16.
 */
public class CategoryDAOTest extends DatabaseInitializer {
    CategoryDAO categoryDAO;

    @Before
    public void setUp() throws Exception {
        prepareConnection();
        super.setUp();
        categoryDAO = new CategoryDAO();
    }

    @After
    public void tearDown() throws Exception {
        prepareConnection();
        super.tearDown();
    }

    @Test
    public void getAll() throws Exception {
        List<Category> result = categoryDAO.getAll();

        assertThat(result, contains(
                new Category("Phone"),
                new Category("Notebook"),
                new Category("Tablets")
        ));
    }

    @Test
    public void getAllCategoriesWithCountedProducts() throws Exception {
        List<String> result = categoryDAO.getAllCategoriesWithCountedProducts();

        List<String> expected = new ArrayList<String>();
        expected.add("Phone " + 2);
        expected.add("Notebook " + 1);
        expected.add("Tablets " + 3);

        assertThat(expected, is(result));
    }
}
