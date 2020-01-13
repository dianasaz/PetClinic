package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Role;
import by.sazanchuk.finalTask.entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceImplTest {
    private static UserService service;
    private static ServiceFactory factory;
    private static User user1;
    private static User user2;
    private static User user3;

    @BeforeClass
    public static void prepare() throws ServiceException {
        factory = new ServiceFactory();
        service = factory.getService(UserService.class);
    }

    @Before
    public void prepareUsers(){
        user1 = new User();
        user1.setName("Masha Kozlova");
        user1.setAddress("Prospect Smysla 23, 12");
        user1.setPassword("user123456");
        user1.setPhoneNumber(291083245);
        user1.setEmail("masha1299@gmail.com");
        user1.setRole(Role.setRole("visitor"));
        user1.setLogin("masha1999");

        user2 = new User();
        user2.setName("Sasha Petrov");
        user2.setAddress("v. Smysla 82, 13");
        user2.setPassword("user123456");
        user2.setPhoneNumber(291234875);
        user2.setEmail("sasha1989@gmail.com");
        user2.setRole(Role.setRole("visitor"));
        user2.setLogin("sasha1979");

        user3 = new User();
        user3.setName("Vanya Serov");
        user3.setAddress("vul. Kozlova 4, 12");
        user3.setPassword("useR123123");
        user3.setPhoneNumber(292433245);
        user3.setEmail("vanya1299@gmail.com");
        user3.setRole(Role.setRole("visitor"));
        user3.setLogin("vanya1999");
    }

    @Test
    public void saveWithoutExceptions() throws ServiceException {
        Integer i = service.save(user1);
        assertNotNull(i);
    }

    @Test (expected = ServiceException.class)
    public void saveException() throws ServiceException {
        user1.setLogin(null);
        service.save(user1);
    }

    @Test
    public void findByIdentity() throws ServiceException {
        service.save(user2);
        User user = service.findByIdentity(user2.getId());

        assertNotNull(user);
    }

    @Test (expected = ServiceException.class)
    public void findByIdentityWithException() throws ServiceException {
        service.findByIdentity(user2.getId());
    }

    @Test
    public void findAll() throws ServiceException {
        service.save(user1);
        service.save(user2);
        service.save(user3);

        List<User> users = service.findAll();
        assertEquals(3, users.size());
    }

    @Test
    public void findByLoginAndPassword() throws ServiceException {
        service.save(user3);

        User user = service.findByLoginAndPassword("vanya1999", "useR123123");

        assertNotNull(user);
    }

    @Test
    public void findByLoginAndPasswordFalse() throws ServiceException {
        User user = service.findByLoginAndPassword("vanya1999", "useR123123");

        assertNull(user);
    }

    @Test
    public void isExist() throws ServiceException {
        service.save(user2);

        assertEquals(true, service.isExist("sasha1979"));
    }

    @Test
    public void isExistButNot() throws ServiceException {
        assertEquals(false, service.isExist("sasha1979"));
    }

    @Test (expected = ServiceException.class)
    public void saveWithException() throws ServiceException {
        service.save(user1);
        user3.setEmail(user1.getEmail());
        service.save(user3);
    }

    @Test
    public void searchEmail() throws ServiceException {
        service.save(user3);

        assertEquals(true, service.searchEmail("vanya1299@gmail.com"));
    }

    @Test
    public void searchEmailThatNotExist() throws ServiceException {
        service.save(user3);
        service.save(user2);
        service.save(user1);

        assertEquals(false, service.searchEmail("vanya@gmail.com"));
    }

    @Test
    public void deleteWithoutException() throws ServiceException{
        service.save(user3);

        service.delete(user3.getId());

        assertEquals(0, service.findAll().size());
    }

    @Test (expected = ServiceException.class)
    public void delete() throws ServiceException{
        service.delete(user3.getId());
    }


    @After
    public void clean() throws ServiceException {
        List<User> users = service.findAll();
        for (User user : users){
            if (service.isExist(user.getLogin())){
                service.delete(user.getId());
            }
        }

    }

}