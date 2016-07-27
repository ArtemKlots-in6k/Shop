import dao.CategoryDAO;
import entity.Category;
import entity.subsidiary.CategoryStatistic;
import entity.Item;
import entity.subsidiary.Top3;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Artem Klots on 7/21/16.
 */
public class CategoryDAOTest extends DatabaseInitializer {
    private CategoryDAO categoryDAO;

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
        List<Top3> expectedResults = new ArrayList<>();
        expectedResults.add(new Top3(new Item(0, "iPhone", category, new BigDecimal("700.00")), 3));
        expectedResults.add(new Top3(new Item(1, "Samsung", category, new BigDecimal("300.50")), 2));
        expectedResults.add(new Top3(new Item(2, "Lenovo", category, new BigDecimal("400.50")), 1));

        assertThat(categoryDAO.getTopThreeItemsInCategory(
                "Phone",
                Date.valueOf(LocalDate.of(2016, 7, 27)),
                Date.valueOf(LocalDate.of(2016, 5, 27))), is(expectedResults));
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
