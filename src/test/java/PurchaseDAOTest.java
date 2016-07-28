import entity.Bill;
import entity.Category;
import entity.Item;
import entity.Purchase;
import org.junit.After;
import org.junit.Before;
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
    public void getPurchaseById() throws Exception {
        Purchase expectedPurchase = new Purchase(
                1,
                itemDAO.getItemById(1),
                new BigDecimal("350"),
                billDAO.getBillById(0)
        );

        assertThat(purchaseDAO.getPurchaseById(1), is(expectedPurchase));
    }

    @Test
    public void getPurchaseByAnotherId() throws Exception {
        Purchase expectedPurchase = new Purchase(
                2,
                itemDAO.getItemById(2),
                new BigDecimal("1300"),
                billDAO.getBillById(0)
        );

        assertThat(purchaseDAO.getPurchaseById(2), is(expectedPurchase));
    }

    @Test
    public void getAllPurchasesByBillId() throws Exception {
        Bill bill = billDAO.getBillById(0);
        Category phoneCategory = new Category("Phone");
        List expected = new ArrayList<>(asList(
                new Purchase(
                        0,
                        new Item(0, "iPhone", phoneCategory, new BigDecimal("700.00")),
                        new BigDecimal(750),
                        bill),
                new Purchase(
                        1,
                        new Item(1, "Samsung", phoneCategory, new BigDecimal("300.50")),
                        new BigDecimal(350),
                        bill),
                new Purchase(
                        2,
                        new Item(2, "Lenovo", phoneCategory, new BigDecimal("400.50")),
                        new BigDecimal(1300.00),
                        bill),
                new Purchase(
                        7,
                        new Item(1, "Samsung", phoneCategory, new BigDecimal("300.50")),
                        new BigDecimal(400),
                        bill)
        ));
        assertThat(purchaseDAO.getAllPurchasesByBillId(0), is(expected));
    }

    @Test
    public void create() throws Exception {
        Purchase newPurchase = purchaseDAO.create(itemDAO.getItemById(1), new BigDecimal("350"), billDAO.getBillById(0));
        assertThat(purchaseDAO.getPurchaseById(8), is(newPurchase));
    }
}
