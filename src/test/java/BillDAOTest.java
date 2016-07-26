import dao.BillDAO;
import dao.UserDao;
import entity.Bill;
import entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Artem Klots on 7/22/16.
 */
public class BillDAOTest extends DatabaseInitializer {
    BillDAO billDAO;

    @Before
    public void setUp() throws Exception {
        prepareConnection();
        super.setUp();
        billDAO = new BillDAO();
    }

    @After
    public void tearDown() throws Exception {
        prepareConnection();
        super.tearDown();
    }

    @Test
    public void getBillById() throws Exception {
        Bill bill = new Bill(0, Date.valueOf(LocalDate.of(2016, 7, 18)), new User(1, "John"));

        Bill expectedBill = billDAO.getBillById(0);

        assertThat(expectedBill, is(bill));
    }

    @Test
    public void getBillByAnotherId() throws Exception {
        Bill bill = new Bill(1, Date.valueOf(LocalDate.of(2016, 7, 18)), new User(0, "Robert"));

        Bill expectedBill = billDAO.getBillById(1);

        assertThat(expectedBill, is(bill));
    }

    @Test
    public void getAll() throws Exception {
        List expected = asList(
                new Bill(Date.valueOf(LocalDate.of(2016, 7, 18)), new User(1, "John")),
                new Bill(Date.valueOf(LocalDate.of(2016, 7, 18)), new User(0, "Robert")),
                new Bill(Date.valueOf(LocalDate.of(2016, 5, 18)), new User(0, "Robert"))
        );

        assertThat(billDAO.getAll(), is(expected));
    }

    @Test
    public void createAndGetAll() throws Exception {
        List expected = asList(
                new Bill(Date.valueOf(LocalDate.of(2016, 7, 18)), new User(1, "John")),
                new Bill(Date.valueOf(LocalDate.of(2016, 7, 18)), new User(0, "Robert")),
                new Bill(Date.valueOf(LocalDate.of(2016, 5, 18)), new User(0, "Robert")),
                new Bill(Date.valueOf(LocalDate.of(2016, 7, 25)), new User(1, "John"))
        );

        billDAO.create(Date.valueOf(LocalDate.of(2016, 7, 25)), new UserDao().getUserById(1));

        assertThat(billDAO.getAll(), is(expected));
    }

    @Ignore
    @Test
    public void getAllBillsByUserId() throws Exception {
        int userId = 1;
        Map result = billDAO.getAllBillsByUserId(userId);
        assertThat(result.get(0).toString(), is("{2800=2016-07-18}"));
    }
}
