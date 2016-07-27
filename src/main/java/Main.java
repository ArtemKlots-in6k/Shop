import dao.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Artem Klots on 7/27/16.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext(new String[]{"config.xml"});
        BillDAO billDAO = (BillDAO) applicationContext.getBean("billDAO");
        CategoryDAO categoryDAO = (CategoryDAO) applicationContext.getBean("categoryDAO");
        ItemDAO itemDAO = (ItemDAO) applicationContext.getBean("itemDAO");
        PurchaseDAO purchaseDAO = (PurchaseDAO) applicationContext.getBean("purchaseDAO");
        UserDao userDAO = (UserDao) applicationContext.getBean("userDAO");

        System.out.println(userDAO.getAll());
    }
}
