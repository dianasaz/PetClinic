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

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * The type Edit doctor command.
 */
public class EditDoctorCommand implements Command {
    private static final Logger logger = LogManager.getLogger(EditDoctorCommand.class);
    private static final String ID = "doctor_id";
    private static final String NAME = "name";
    private static final String SERVICES = "service";
    private static final String ERROR_UPDATE = "error_update";
    private static final String VALID = "valid";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String doctor_id = (String) request.getSession().getAttribute(ID);
        String name = request.getParameter(NAME);
        String[] service = request.getParameterValues(SERVICES);

        if (name == null && service == null) {
            String id = request.getParameter(ID);
            request.getSession().setAttribute(ID, id);
            return goBackWithError(request, ERROR_UPDATE);
        } else {
            try {
                ServiceFactory factory = new ServiceFactory();
                ServiceService serviceService = factory.getService(ServiceService.class);
                DoctorService doctorService = factory.getService(DoctorService.class);

                Doctor oldDoctor = searchDoctor(doctor_id);
                Doctor doctor = new Doctor();
                doctor.setIdentity(oldDoctor.getIdentity());
                if (name == null || name.isEmpty()) {
                    doctor.setName(oldDoctor.getName());
                } else {
                    doctor.setName(name);
                    if (isValid(doctor).equals(VALID)) {
                        doctorService.save(doctor);
                    } else return goBackWithError(request, isValid(doctor));
                }
                if (service == null || service.length == 0) {
                    doctor.setService(oldDoctor.getService());
                } else {
                    List<String> services = Arrays.asList(service);
                    doctor.removeServices();
                    doctorService.deleteReferences(doctor);
                    for (String s : services) {
                        Service service1 = serviceService.searchServiceByName(s);
                        if (service1 != null) {
                            doctor.addService(service1);
                            doctorService.save(doctor, service1);
                        }
                    }
                }

                request.getSession().removeAttribute(ID);
                return new CommandResult("/controller?command=watch_doctor", false);
            } catch (ServiceException e){
                logger.log(Level.INFO, e.getMessage());
                return goBackWithError(request, e.getMessage());
            }
        }
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.edit_doctor"), false);
    }

    private Doctor searchDoctor(String id) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        DoctorService service = factory.getService(DoctorService.class);
        if (service != null) {
            return service.findByIdentity(Integer.valueOf(id));
        } else return null;
    }

    private String isValid(Doctor doctor){
        DoctorValidator doctorValidator = DoctorValidator.getValidator();
        return doctorValidator.isValid(doctor);
    }
}

