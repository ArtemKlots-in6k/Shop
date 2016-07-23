import dao.PurchaseDAO;
import entity.Purchase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Artem Klots on 23.07.2016.
 */
public class PurchaseDAOTest extends DatabaseInitializer {
    private PurchaseDAO purchaseDAO;

    @Before
    public void setUp() throws Exception {
        prepareConnection();
        super.setUp();
        purchaseDAO = new PurchaseDAO();
    }

    @After
    public void tearDown() throws Exception {
        prepareConnection();
        super.tearDown();
    }

    @Test
    public void getAll() throws Exception {
        assertThat(purchaseDAO.getAll().size(), is(4));
    }

    @Test
    public void getPurchaseById() throws Exception {
        assertThat(purchaseDAO.getPurchaseById(1).getId(), is(1));
    }
}
