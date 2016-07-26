import dao.CategoryDAO;
import entity.Category;
import entity.CategoryStatistic;
import entity.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Artem Klots on 7/21/16.
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
    public void getCategoryById() throws Exception {
        Category expectedCategory = new Category(1, "Notebook");
        assertThat(categoryDAO.getCategoryById(1), is(expectedCategory));
    }

    @Test
    public void getCategoryByAnotherId() throws Exception {
        Category expectedCategory = new Category(2, "Tablets");
        assertThat(categoryDAO.getCategoryById(2), is(expectedCategory));
    }

    @Test
    public void getCategoryByTitle() throws Exception {
        assertThat(categoryDAO.getCategoryByTitle("Phone").getTitle(), is("Phone"));
    }

    @Test
    public void getCategoryByAnotherTitle() throws Exception {
        assertThat(categoryDAO.getCategoryByTitle("Notebook").getTitle(), is("Notebook"));
    }

    @Test
    public void getTopThreeItemsInCategory() throws Exception {
        Category category = new Category("Phone");
        Map<Item, Integer> map = new LinkedHashMap<>();
        map.put(new Item("iPhone", category, new BigDecimal("700.00")), 3);
        map.put(new Item("Samsung", category, new BigDecimal("300.50")), 2);
        map.put(new Item("Lenovo", category, new BigDecimal("400.50")), 1);

        assertThat(categoryDAO.getTopThreeItemsInCategory("Phone"), is(map));
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
        List<CategoryStatistic> expected = new ArrayList<>();
        expected.add(new CategoryStatistic(0, "Phone", 3));
        expected.add(new CategoryStatistic(1, "Notebook", 1));
        expected.add(new CategoryStatistic(2, "Tablets", 3));

        assertThat(categoryDAO.getAllCategoriesWithCountedProducts(), is(expected));
    }
}
