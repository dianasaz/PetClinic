package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.entity.Coupon;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.Pet;
import by.sazanchuk.finalTask.entity.PetList;
import by.sazanchuk.finalTask.entity.Role;
import by.sazanchuk.finalTask.entity.Service;
import by.sazanchuk.finalTask.entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CouponServiceImplTest {
    private static CouponService couponService;
    private static ServiceService serviceService;
    private static UserService userService;
    private static PetService petService;
    private static DoctorService doctorService;
    private static ServiceFactory factory;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat dateFormatPet = new SimpleDateFormat("dd.MM.yyyy");
    private static Coupon coupon1;
    private static Coupon coupon2;
    private static Doctor doctor;
    private static Service service;
    private static Pet pet;
    private static User user;

    @BeforeClass
    public static void prepare() throws ServiceException {
        factory = new ServiceFactory();
        couponService = factory.getService(CouponService.class);
        serviceService = factory.getService(ServiceService.class);
        petService = factory.getService(PetService.class);
        userService = factory.getService(UserService.class);
        doctorService = factory.getService(DoctorService.class);
    }

    @Before
    public void prepareCoupons() throws ParseException {
        String date = "2019-09-03 10:00";
        Date date1 = dateFormat.parse(date);

        coupon1 = new Coupon();
        coupon1.setTime(date1);

        coupon2 = new Coupon();
        coupon2.setTime(date1);

        user = new User();
        user.setName("Masha Kozlova");
        user.setAddress("Prospect Smysla 23, 12");
        user.setPassword("user123456");
        user.setPhoneNumber(291083245);
        user.setEmail("masha1299@gmail.com");
        user.setRole(Role.setRole("visitor"));
        user.setLogin("masha1999");

        pet = new Pet();
        pet.setName("Kiko");
        pet.setKind(PetList.CAT);
        pet.setDateOfBirth(dateFormatPet.parse("07.12.2012"));

        doctor = new Doctor();
        doctor.setName("Vlada Frodo");

        service = new Service();
        service.setName("Vakcinacia");
        service.setPrice(1500);
    }

    @Test
    public void findAll() throws ServiceException {
        serviceService.save(service);
        doctorService.save(doctor, service);
        userService.save(user);
        pet.setUser_identity(user.getId());
        petService.save(pet);

        coupon1.setUser_id(user.getId());
        coupon1.setPet_id(pet.getIdentity());
        coupon1.setDoctor_id(doctor.getIdentity());
        coupon1.setService_id(service.getIdentity());
        couponService.save(coupon1);

        List<Coupon> coupons = couponService.findAll();
        assertEquals(1, coupons.size());
    }

    @Test
    public void findByIdentity() throws ServiceException {
        serviceService.save(service);
        doctorService.save(doctor, service);
        userService.save(user);
        pet.setUser_identity(user.getId());
        petService.save(pet);

        coupon1.setUser_id(user.getId());
        coupon1.setPet_id(pet.getIdentity());
        coupon1.setDoctor_id(doctor.getIdentity());
        coupon1.setService_id(service.getIdentity());
        couponService.save(coupon1);

        assertNotNull(couponService.findByIdentity(coupon1.getIdentity()));
    }

    @Test (expected = ServiceException.class)
    public void findByIdentityException() throws ServiceException {
      couponService.findByIdentity(coupon1.getIdentity());
    }

    @Test (expected = ServiceException.class)
    public void saveWithException() throws ServiceException {
        couponService.save(null);
    }

    @Test
    public void save() throws ServiceException {
        serviceService.save(service);
        doctorService.save(doctor, service);
        userService.save(user);
        pet.setUser_identity(user.getId());
        petService.save(pet);

        coupon1.setUser_id(user.getId());
        coupon1.setPet_id(pet.getIdentity());
        coupon1.setDoctor_id(doctor.getIdentity());
        coupon1.setService_id(service.getIdentity());
        couponService.save(coupon1);

        assertNotNull(couponService.findByIdentity(coupon1.getIdentity()));
    }

    @Test
    public void savemy() throws ServiceException, ParseException {
        serviceService.save(service);
        doctorService.save(doctor, service);
        userService.save(user);
        pet.setUser_identity(user.getId());
        petService.save(pet);

        String date = "2019-09-09 10:00";
        Date date8 = dateFormat.parse(date);

        coupon1.setUser_id(user.getId());
        coupon1.setPet_id(pet.getIdentity());
        coupon1.setDoctor_id(doctor.getIdentity());
        coupon1.setService_id(service.getIdentity());
        couponService.save(coupon1);

        Coupon newCoupon = new Coupon();
        newCoupon.setIdentity(coupon1.getIdentity());
        newCoupon.setUser_id(coupon1.getUser_id());
        newCoupon.setPet_id(coupon1.getPet_id());
        newCoupon.setService_id(coupon1.getService_id());
        newCoupon.setDoctor_id(coupon1.getDoctor_id());
        newCoupon.setTime(date8);

        couponService.save(newCoupon);
    }

    @Test (expected = ServiceException.class)
    public void deleteException() throws ServiceException {
        couponService.delete(null);
    }

    @Test
    public void delete() throws ServiceException {
        serviceService.save(service);
        doctorService.save(doctor, service);
        userService.save(user);
        pet.setUser_identity(user.getId());
        petService.save(pet);

        coupon1.setUser_id(user.getId());
        coupon1.setPet_id(pet.getIdentity());
        coupon1.setDoctor_id(doctor.getIdentity());
        coupon1.setService_id(service.getIdentity());
        couponService.save(coupon1);

        couponService.delete(coupon1.getIdentity());
        assertNull(couponService.findByIdentity(coupon1.getIdentity()));
    }

    @Test (expected = ServiceException.class)
    public void saveException() throws ServiceException {
        serviceService.save(service);
        doctorService.save(doctor, service);
        userService.save(user);


        coupon1.setUser_id(user.getId());
        coupon1.setDoctor_id(doctor.getIdentity());
        coupon1.setService_id(service.getIdentity());
        couponService.save(coupon1);
    }

    @Test
    public void isTaken() throws ServiceException {
            serviceService.save(service);
            doctorService.save(doctor, service);
            userService.save(user);
            pet.setUser_identity(user.getId());
            petService.save(pet);

            coupon1.setUser_id(user.getId());
            coupon1.setPet_id(pet.getIdentity());
            coupon1.setDoctor_id(doctor.getIdentity());
            coupon1.setService_id(service.getIdentity());
            couponService.save(coupon1);

            assertTrue(couponService.isTaken(doctor.getIdentity(), coupon1.getTime()));

    }


    @After
    public void clean() throws ServiceException {
        List<Coupon> coupons = couponService.findAll();
        for (Coupon couponEntity : coupons){
            if (couponService.findByIdentity(couponEntity.getIdentity()) != null){
                couponService.delete(couponEntity.getIdentity());
            }
        }

        if (user.getId() != null) userService.delete(user.getId());

        if (pet.getIdentity() != null) petService.delete(pet.getIdentity());

        if (service.getIdentity() != null) serviceService.delete(service.getIdentity());

        if (doctor.getIdentity() != null) doctorService.delete(doctor.getIdentity());
    }
}
