import dao.BillDAO;
import entity.Bill;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        BillDAO billDAO = new BillDAO();

        Bill expectedBill = billDAO.getBillById(0);
        assertThat(expectedBill.getId(), is(0));
    }
    @Test
    public void getAll() throws Exception {
        BillDAO billDAO = new BillDAO();

        List<Bill> expect = new ArrayList<Bill>();

        assertThat(billDAO.getAll().size(), is(2));
    }
}
