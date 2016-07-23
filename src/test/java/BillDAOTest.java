import dao.BillDAO;
import entity.Bill;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Bill expectedBill = billDAO.getBillById(0);
        assertThat(expectedBill.getId(), is(0));
    }
    @Test
    public void getAll() throws Exception {
        assertThat(billDAO.getAll().size(), is(3));
    }

    @Test
    public void getAllBillsByUserId() throws Exception {
        int userId = 1;
        Map result = billDAO.getAllBillsByUserId(userId);
        assertThat(result.get(0).toString(), is("{2800=2016-07-18}"));
    }
}
