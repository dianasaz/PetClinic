package by.sazanchuk.finalTask.controller.command.action.doctor;

import by.sazanchuk.finalTask.controller.command.ConfigurationManager;
import by.sazanchuk.finalTask.controller.command.action.Command;
import by.sazanchuk.finalTask.controller.command.action.CommandResult;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.service.DoctorService;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The type Watch doctor command.
 */
public class WatchDoctorCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        List<Doctor> doctors = null;
        try {
            doctors = getAllDoctors();
        } catch (ServiceException e) {
           return goBackWithError(request, e.getMessage());
        }
        request.setAttribute("doctors", doctors);
        return new CommandResult(ConfigurationManager.getProperty("path.page.doctor"), false);
    }

    private List<Doctor> getAllDoctors() throws ServiceException {

        ServiceFactory factory = new ServiceFactory();
        DoctorService service = factory.getService(DoctorService.class);
        List<Doctor> doctors = service.findAll();
        return doctors;
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.doctor"), false);
    }

}
