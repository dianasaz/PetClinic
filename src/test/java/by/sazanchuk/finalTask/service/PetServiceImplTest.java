package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Pet;
import by.sazanchuk.finalTask.entity.PetList;
import by.sazanchuk.finalTask.entity.Role;
import by.sazanchuk.finalTask.entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PetServiceImplTest {
    private static PetService service;
    private static UserService userService;
    private static ServiceFactory factory;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private static Pet pet1;
    private static Pet pet2;
    private static User user;

    @BeforeClass
    public static void prepare() throws ServiceException {
        factory = new ServiceFactory();
        service = factory.getService(PetService.class);
        userService = factory.getService(UserService.class);
    }

    @Before
    public void preparePets() throws ParseException {
        pet1 = new Pet();
        pet1.setName("Kiko");
        pet1.setKind(PetList.CAT);
        pet1.setDateOfBirth(dateFormat.parse("07.12.2012"));

        pet2 = new Pet();
        pet2.setKind(PetList.CAT);
        pet2.setName("Homa");
        pet1.setDateOfBirth(dateFormat.parse("12.12.2017"));

        user = new User();
        user.setName("Masha Kozlova");
        user.setAddress("Prospect Smysla 23, 12");
        user.setPassword("user123456");
        user.setPhoneNumber(291083245);
        user.setEmail("masha1299@gmail.com");
        user.setRole(Role.setRole("visitor"));
        user.setLogin("masha1999");
    }

    @Test (expected = ServiceException.class)
    public void findAllWithException() throws ServiceException {
        service.save(pet2);
        service.findAll();
    }

    @Test
    public void findAll() throws ServiceException {
        userService.save(user);

        pet1.setUser_identity(user.getId());
        pet2.setUser_identity(user.getId());

        service.save(pet2);
        service.save(pet1);
        List<Pet> pets = service.findAll();

        assertEquals(2, pets.size());
    }

    @Test (expected = ServiceException.class)
    public void findByIdentityWithException() throws ServiceException {
        service.findByIdentity(null);
    }

    @Test
    public void findByIdentity() throws ServiceException {
        userService.save(user);

        pet2.setUser_identity(user.getId());

        service.save(pet2);

        assertNotNull(service.findByIdentity(pet2.getIdentity()));
    }

    @Test
    public void findByIdentityFalse() throws ServiceException {
        assertNull(service.findByIdentity(4));
    }

    @Test
    public void save() throws ServiceException {
        userService.save(user);

        pet2.setUser_identity(user.getId());

        Integer i = service.save(pet2);

        assertNotNull(i);
    }

    @Test (expected = ServiceException.class)
    public void saveWithException() throws ServiceException {
        service.save(null);
    }

    @Test
    public void delete() throws ServiceException {
        userService.save(user);
        pet2.setUser_identity(user.getId());
        service.save(pet2);
        service.delete(pet2.getIdentity());

        assertNull(service.findByIdentity(pet2.getIdentity()));
    }

    @Test (expected = ServiceException.class)
    public void deleteWithException() throws ServiceException {
        service.delete(null);
    }

    @Test
    public void getPetsOfOneUser() throws ServiceException {
        userService.save(user);

        pet1.setUser_identity(user.getId());
        pet2.setUser_identity(user.getId());

        service.save(pet2);
        service.save(pet1);

        List<Pet> pets = service.getPetsOfOneUser(user.getId());

        assertEquals(2, pets.size());
    }

    @Test (expected = ServiceException.class)
    public void getPetsOfOneUserWithException() throws ServiceException {
        service.getPetsOfOneUser(null);
    }

    @Test
    public void findByNameAndUserId() throws ServiceException {
        userService.save(user);
        pet2.setUser_identity(user.getId());
        service.save(pet2);

        Pet pet = service.findByNameAndUserId(pet2.getName(), user.getId());

        assertNotNull(pet);
    }

    @Test (expected = ServiceException.class)
    public void findByNameAndUserIdWithException() throws ServiceException {
        service.findByNameAndUserId(null, 1);
    }


    @After
    public void clean() throws ServiceException {
        List<Pet> pets = service.findAll();
        for (Pet petEntity : pets){
            if (service.findByIdentity(petEntity.getIdentity()) != null){
                service.delete(petEntity.getIdentity());
            }
        }

        if (user.getId() != null) {
            userService.delete(user.getId());
        }

    }
}