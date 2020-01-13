package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.Service;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ServiceServiceImplTest {
    private static ServiceService service;
    private static ServiceFactory factory;
    private static Service service1;
    private static Service service2;
    private static Service service3;

    @BeforeClass
    public static void prepare() throws ServiceException {
        factory = new ServiceFactory();
        service = factory.getService(ServiceService.class);
    }

    @Before
    public void prepareServices(){
        service1 = new Service();
        service1.setName("Microchipping");
        service1.setPrice(1000);

        service2 = new Service();
        service2.setName("Vakcinacia");
        service2.setPrice(1500);

        service3 = new Service();
        service3.setName("Sterilizacia");
        service3.setPrice(2000);
    }

    @Test
    public void saveWithoutExceptions() throws ServiceException {
        Integer i = service.save(service1);

        assertNotNull(i);
    }

    @Test (expected = ServiceException.class)
    public void saveWithException() throws ServiceException {
        Service s = null;
        service.save(s);
    }

    @Test
    public void findAll() throws ServiceException {
        service.save(service1);
        service.save(service2);
        service.save(service3);

        List<Service> services = service.findAll();

        assertEquals(3, services.size());
    }

    @Test (expected = ServiceException.class)
    public void searchWithOneDoctor() throws ServiceException {
        service.searchWithOneDoctor(null);
    }

    @Test
    public void findByIdentity() throws ServiceException {
        service.save(service1);

        assertNotNull(service.findByIdentity(service1.getIdentity()));
    }

    @Test (expected = ServiceException.class)
    public void findByIdentityWithException() throws ServiceException {
        service.findByIdentity(null);
    }

    @Test
    public void findByIdentityFalse() throws ServiceException {
        assertNull(service.findByIdentity(5));
    }

    @Test
    public void delete() throws ServiceException {
        service.save(service1);

        service.delete(service1.getIdentity());

        assertNull(service.findByIdentity(service1.getIdentity()));
    }

    @Test (expected = ServiceException.class)
    public void deleteWithException() throws ServiceException {
        service.delete(null);
    }

    @Test (expected = ServiceException.class)
    public void searchServiceWithException() throws ServiceException {
        service.searchService(null);
    }


    @Test
    public void searchService() throws ServiceException {
        service.save(service2);

        assertTrue(service.searchService("Vakcinacia"));
    }

    @Test
    public void searchServiceFalse() throws ServiceException {
        assertFalse(service.searchService("Vakcinacia"));
    }

    @Test
    public void searchServiceByName() throws ServiceException {
        service.save(service3);

        assertEquals(service3.getIdentity(), service.searchServiceByName("Sterilizacia").getIdentity());
    }

    @Test
    public void searchServiceByPrice() throws ServiceException {
        service.save(service3);

        assertEquals(1, service.searchServiceByPrice(2000).size());
    }

    //DELETE POTOM todo
    @Test
    public void myTestAfterDelete() throws ServiceException {


        DoctorService doctorService = factory.getService(DoctorService.class);
        for (Doctor d: doctorService.findByName("Sterili")){
            doctorService.delete(d.getIdentity());
        }

        Service service4 = new Service();
        service4.setName("Sterili");
        service4.setPrice(2000);
        service.save(service4);

        Doctor doctor = new Doctor();
        doctor.setName("Sterili");

        String search = "2000";

        doctorService.save(doctor, service4);

        List<Object> objects = new ArrayList<>();
        List<Doctor> doctors = doctorService.findByName(search);//КОСЯЯЯЯЯЯЯЯЯЯЯЯК
        List<Service> services = null;

        try {
            int s = Integer.parseInt(search);
            if (s != 0) {
                services = service.searchServiceByPrice(s);
            }
        } catch (NumberFormatException e) {}

        Service se = service.searchServiceByName(search);
        if (doctors != null) objects.addAll(doctors);
        if (services != null) objects.addAll(services);
        if (se != null) objects.add(se);

        assertEquals(1, objects.size());
    }

    @Test (expected = ServiceException.class)
    public void searchServiceByNameException() throws ServiceException {
        service.searchServiceByName(null);
    }

    @Test
    public void searchByNamePart() throws ServiceException {
        service.save(service1);
        service.save(service2);
        service.save(service3);

        assertEquals(2, service.searchServiceByPartOfName("a").size());
    }

    @After
    public void clean() throws ServiceException {
        List<Service> services = service.findAll();
        for (Service serviceEntity : services){
            if (service.searchService(serviceEntity.getName())){
                service.delete(serviceEntity.getIdentity());
            }
        }

    }

}