import dao.BillDAO;
import entity.Bill;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    public void getAll() throws Exception {
        BillDAO billDAO = new BillDAO();

        List<Bill> expect = new ArrayList<Bill>();
//        expect.add(new Bill(0, "Robert"));
//        expect.add(new Bill(1, "John"));

        System.out.println(billDAO.getAll());

//        assertThat(billDAO.getAll(), is(expect));
    }
}
