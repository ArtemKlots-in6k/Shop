import dao.UserDao;
import entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Artem Klots on 7/22/16.
 */
public class UserDAOTest extends DatabaseInitializer {
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        prepareConnection();
        super.setUp();
        userDao = new UserDao();
    }

    @After
    public void tearDown() throws Exception {
        prepareConnection();
        super.tearDown();
    }

    @Test
    public void createAndGetByName() throws Exception {
        assertThat(userDao.getUserByName("John").getName(), is("John"));
    }

    @Test
    public void createAndGetAll() throws Exception {
        userDao.create("Bob");
        assertThat(userDao.getAll().size(), is(3));
    }

    @Test
    public void getAll() throws Exception {

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
