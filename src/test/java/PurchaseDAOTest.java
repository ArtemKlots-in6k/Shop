import dao.BillDAO;
import dao.ItemDAO;
import dao.PurchaseDAO;
import entity.Bill;
import entity.Category;
import entity.Item;
import entity.Purchase;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
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
    public void getPurchaseById() throws Exception {
        Purchase expectedPurchase = new Purchase(
                1,
                new ItemDAO().getItemById(1),
                new BigDecimal("350"),
                new BillDAO().getBillById(0)
        );

        assertThat(purchaseDAO.getPurchaseById(1), is(expectedPurchase));
    }

    @Test
    public void getPurchaseByAnotherId() throws Exception {
        Purchase expectedPurchase = new Purchase(
                2,
                new ItemDAO().getItemById(2),
                new BigDecimal("1300"),
                new BillDAO().getBillById(0)
        );

        assertThat(purchaseDAO.getPurchaseById(2), is(expectedPurchase));
    }

    @Test
    public void getAllPurchasesByBillId() throws Exception {
        Bill bill = new BillDAO().getBillById(0);
        Category phoneCategory = new Category("Phone");
        List expected = asList(
                new Purchase(
                        new Item("iPhone", phoneCategory, new BigDecimal("700.00")),
                        new BigDecimal(750),
                        bill),
                new Purchase(
                        new Item("Samsung", phoneCategory, new BigDecimal("300.50")),
                        new BigDecimal(350),
                        bill),
                new Purchase(
                        new Item("Lenovo", phoneCategory, new BigDecimal("400.50")),
                        new BigDecimal(1300.00),
                        bill),
                new Purchase(
                        new Item("Samsung", phoneCategory, new BigDecimal("300.50")),
                        new BigDecimal(400),
                        bill)
        );
        assertThat(purchaseDAO.getAllPurchasesByBillId(0), is(expected));
    }

    @Test
    public void create() throws Exception {
        Purchase newPurchase = purchaseDAO.create(new ItemDAO().getItemById(1), new BigDecimal("350"), new BillDAO().getBillById(0));
        assertThat(purchaseDAO.getPurchaseById(8), is(newPurchase));
    }
}
