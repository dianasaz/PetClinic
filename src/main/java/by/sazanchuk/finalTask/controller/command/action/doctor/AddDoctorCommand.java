package by.sazanchuk.finalTask.controller.command.action.doctor;

import by.sazanchuk.finalTask.controller.command.ConfigurationManager;
import by.sazanchuk.finalTask.controller.command.action.Command;
import by.sazanchuk.finalTask.controller.command.action.CommandResult;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.Service;
import by.sazanchuk.finalTask.service.DoctorService;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import by.sazanchuk.finalTask.service.ServiceService;
import by.sazanchuk.finalTask.validator.DoctorValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * The type Add doctor command.
 */
public class AddDoctorCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddDoctorCommand.class);
    private static final String NAME = "name";
    private static final String SERVICES = "service";
    private static final String ERROR_EXIST = "error_exist";
    private static final String ERROR_NULL = "error_null";
    private static final String VALID = "valid";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(NAME);
        String[] service = request.getParameterValues(SERVICES);

        if (name == null || name.isEmpty() || service == null ||service.length == 0) {
            logger.log(Level.INFO, "name or price is null");
            return goBackWithError(request, ERROR_NULL);
        } else {
            try {
                if (!searchDoctor(name)) { /*todo*/
                    List<String> services = Arrays.asList(service);
                    ServiceFactory factory = new ServiceFactory();
                    ServiceService serviceService = factory.getService(ServiceService.class);
                    DoctorService doctorService = factory.getService(DoctorService.class);
                    Doctor d = new Doctor();
                    d.setName(name);
                    if (isValid(d).equals(VALID)) {
                        for (String s : services) {
                            Service service1 = serviceService.searchServiceByName(s);
                            if (service1 != null) {
                                d.addService(service1);
                                doctorService.save(d, service1);
                            }
                        }
                        return new CommandResult("/controller?command=watch_doctor", false); //TODO
                    } else return goBackWithError(request, isValid(d));
                } else {
                    return goBackWithError(request, ERROR_EXIST);
                }

            } catch (ServiceException e) {
                logger.log(Level.INFO, e.getMessage());
                return goBackWithError(request, e.getMessage());
            }
        }
    }


    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.add_doctor"), false);
    }

    private String isValid(Doctor doctor) {
        DoctorValidator doctorValidator = DoctorValidator.getValidator();
        return doctorValidator.isValid(doctor);
    }


    private boolean searchDoctor(String name) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        DoctorService service = factory.getService(DoctorService.class);

        return service.findByName(name).size() != 0;
    }
}