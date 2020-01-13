package by.sazanchuk.finalTask.controller.command.action.coupon;

import by.sazanchuk.finalTask.controller.command.ConfigurationManager;
import by.sazanchuk.finalTask.controller.command.action.Command;
import by.sazanchuk.finalTask.controller.command.action.CommandResult;
import by.sazanchuk.finalTask.entity.Coupon;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.Pet;
import by.sazanchuk.finalTask.entity.Service;
import by.sazanchuk.finalTask.entity.User;
import by.sazanchuk.finalTask.service.CouponService;
import by.sazanchuk.finalTask.service.DoctorService;
import by.sazanchuk.finalTask.service.PetService;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import by.sazanchuk.finalTask.service.ServiceService;
import by.sazanchuk.finalTask.validator.CouponValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Take coupon command.
 */
public class TakeCouponCommand implements Command {
    private static final Logger log = LogManager.getLogger(TakeCouponCommand.class);
    private static final String PET_ID = "pet_id";
    private static final String USER = "user";
    private static final String SERVICE = "service";
    private static final String DOCTOR = "doctor";
    private static final String DATE = "date";
    private static final String SERVIES_NAMES = "serviceNames";
    private static final String ERROR_TIME = "error_time";
    private static final String VALID = "valid";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(USER);
        String pet_id = request.getParameter(PET_ID);
        String serviceNumber = request.getParameter(SERVICE);
        String doctor = request.getParameter(DOCTOR + serviceNumber);
        String date = request.getParameter(DATE);

        if (serviceNumber == null || doctor == null || user == null || date == null || serviceNumber.isEmpty() || doctor.isEmpty() || date.isEmpty()) {
            log.log(Level.INFO, "invalid info");
            request.getSession().setAttribute("pet_id", pet_id);
            return goBackWithError(request, "error");
        } else {
            try {
                Pet pet = searchPet(pet_id);
                String[] strings = (String[]) request.getSession().getAttribute(SERVIES_NAMES);
                String service = strings[Integer.valueOf(serviceNumber) - 1];
                Service service1 = getService(service);
                int serviceId = service1.getIdentity();
                ServiceFactory factory = new ServiceFactory();
                Doctor d = getDoctor(doctor);
                int doctorId = d.getIdentity();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                CouponService couponService = factory.getService(CouponService.class);
                Date date1 = null;
                try {
                    date1 = dateFormat.parse(date);
                } catch (ParseException e) {
                    log.log(Level.INFO, e.getMessage());
                    goBackWithError(request, e.getMessage());
                }
                if (!couponService.isTaken(d.getIdentity(), date1)) {
                    Coupon coupon = new Coupon();
                    coupon.setPet_id(pet.getIdentity());
                    coupon.setUser_id(pet.getUser_identity());
                    coupon.setDoctor_id(doctorId);
                    coupon.setService_id(serviceId);
                    coupon.setTime(date1);
                    if (isValid(coupon).equals(VALID)) {
                        couponService.save(coupon);
                        request.getSession().removeAttribute("pet_id");
                        return new CommandResult("/controller?command=profile", false);
                    } else return goBackWithError(request, isValid(coupon));
                } else return goBackWithError(request, ERROR_TIME);
            } catch (ServiceException e) {
                log.log(Level.INFO, e.getMessage());
                return goBackWithError(request, e.getMessage());
            }
        }
    }


    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.take_coupon"), false);
    }

    private Service getService(String s) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        ServiceService serviceService = factory.getService(ServiceService.class);
        return serviceService.searchServiceByName(s);
    }

    private Doctor getDoctor(String id) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        DoctorService doctorService = factory.getService(DoctorService.class);
        return doctorService.findByIdentity(Integer.parseInt(id));
    }

    private String isValid(Coupon coupon){
        CouponValidator couponValidator = CouponValidator.getValidator();
        return couponValidator.isValid(coupon);
    }


    private Pet searchPet(String pet_id) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        PetService petService = factory.getService(PetService.class);
        Pet pet = petService.findByIdentity(Integer.valueOf(pet_id));
        return pet;
    }

}
