package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.Service;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.print.Doc;
import java.util.List;

import static org.junit.Assert.*;

public class DoctorServiceImplTest {

    private static DoctorService service;
    private static ServiceService serviceService;
    private static ServiceFactory factory;
    private static Doctor doctor1;
    private static Doctor doctor2;
    private static Doctor doctor3;
    private static Service service1;
    private static Service service2;

    @BeforeClass
    public static void prepare() throws ServiceException {
        factory = new ServiceFactory();
        service = factory.getService(DoctorService.class);
        serviceService = factory.getService(ServiceService.class);
    }

    @Before
    public void prepareDoctors() {
        doctor1 = new Doctor();
        doctor1.setName("Vlada Frodo");

        doctor2 = new Doctor();
        doctor2.setName("Gosha Trevi");

        doctor3 = new Doctor();
        doctor3.setName("Gosha Tr");

        service1 = new Service();
        service1.setName("Microchipping");
        service1.setPrice(500);

        service2 = new Service();
        service2.setName("Vakcinacia");
        service2.setPrice(1000);
    }

    @Test
    public void save() throws ServiceException {
        service.save(doctor1);

        assertNotNull(service.findByIdentity(doctor1.getIdentity()));
    }

    @Test (expected = ServiceException.class)
    public void saveWithException() throws ServiceException {
        service.save(null);
    }

    @Test
    public void saveWithService()throws ServiceException {
        serviceService.save(service1);
        service.save(doctor1, service1);

        assertNotNull(doctor1);
    }

    @Test (expected = ServiceException.class)
    public void saveWithServiceException() throws ServiceException {
        service.save(doctor2, service2);
    }

    @Test
    public void findAll() throws ServiceException{
        serviceService.save(service1);
        serviceService.save(service2);
        service.save(doctor1, service1);
        service.save(doctor1, service2);
        service.save(doctor2, service2);
        List<Doctor> doctors = service.findAll();

        assertEquals(2, doctors.size());
    }

    @Test (expected = ServiceException.class)
    public void findByIdentityWithException() throws ServiceException {
        service.findByIdentity(null);
    }

    @Test
    public void findByIdentity() throws ServiceException {
        serviceService.save(service2);
        service.save(doctor2, service2);

        assertNotNull(service.findByIdentity(doctor2.getIdentity()));
    }

    @Test
    public void findByIdentityFalse() throws ServiceException {
        assertNull(service.findByIdentity(-56));
    }

    @Test (expected = ServiceException.class)
    public void findByNameWithException() throws ServiceException {
        service.findByName(null);
    }

    @Test
    public void findByName() throws ServiceException {
        serviceService.save(service2);
        service.save(doctor2, service2);
        service.save(doctor3, service2);

        assertEquals(2, service.findByName("Gosha").size());
    }

    @Test (expected = ServiceException.class)
    public void deleteWithException() throws ServiceException {
        service.delete(null);
    }

    @Test
    public void delete() throws ServiceException {
        serviceService.save(service2);
        service.save(doctor2, service2);

        service.delete(doctor2.getIdentity());
        assertNull(service.findByName(doctor2.getName()));
    }

    @After
    public void clean() throws ServiceException {
        List<Doctor> doctors = service.findAll();
        for (Doctor doctorEntity : doctors){
            if (service.findByIdentity(doctorEntity.getIdentity()) != null){
                service.delete(doctorEntity.getIdentity());
            }
        }

        List<Service> services = serviceService.findAll();
        for (Service serviceEntity : services){
            if (serviceService.findByIdentity(serviceEntity.getIdentity()) != null){
                serviceService.delete(serviceEntity.getIdentity());
            }
        }

    }


}

