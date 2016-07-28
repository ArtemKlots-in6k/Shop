import entity.Bill;
import entity.User;
import entity.subsidiary.UserBill;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Artem Klots on 7/22/16.
 */
public class BillDAOTest extends DatabaseInitializer {

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

        billDAO.create(Date.valueOf(LocalDate.of(2016, 7, 25)), userDao.getUserById(1));

        assertThat(billDAO.getAll(), is(expected));
    }

    @Test
    public void getAllBillsByUserId() throws Exception {
        int userId = 0;
        List expectedList = asList(
                new UserBill(new Bill(2, Date.valueOf(LocalDate.of(2016, 5, 18)), new User("Robert")), new BigDecimal(2600)),
                new UserBill(new Bill(1, Date.valueOf(LocalDate.of(2016, 7, 18)), new User("Robert")), new BigDecimal(1400))
        );
        assertThat(billDAO.getAllBillsByUserId(userId), is(expectedList));
    }

    @Test
    public void getAllBillsByAnotherUserId() throws Exception {
        int userId = 1;
        List expectedList = asList(
                new UserBill(new Bill(0, Date.valueOf(LocalDate.of(2016, 7, 18)), new User("John")), new BigDecimal(2800))
        );
        assertThat(billDAO.getAllBillsByUserId(userId), is(expectedList));
    }
}
