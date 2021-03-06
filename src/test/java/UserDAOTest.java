import dao.UserDao;
import entity.User;
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
public class UserDAOTest extends DatabaseInitializer {
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
        UserDao userDao = new UserDao();

        List<User> expect = new ArrayList<>();
        expect.add(new User(0, "Robert"));
        expect.add(new User(1, "John"));

        assertThat(userDao.getAll(), is(expect));
    }

    @Test
    public void getUserById() throws Exception {
        UserDao userDao = new UserDao();

        assertThat(userDao.getUserById(1).getId(), is(1));
    }
}
