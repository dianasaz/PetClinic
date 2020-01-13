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

/**
 * The type Delete doctor command.
 */
public class DeleteDoctorCommand implements Command {
    private static final String DOCTOR_ID = "doctor_id";
    private static final String ERROR_DELETE = "error_delete";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter(DOCTOR_ID);

        try {
            if (name != null | !name.isEmpty()) {
                deleteDoctor(name, request);
                return new CommandResult("/controller?command=watch_doctor", false);
            } else {
                request.setAttribute(ERROR_DELETE, true);
                return goBackWithError(request, "can't delete doctor");
            }
        } catch (ServiceException e) {
            return goBackWithError(request, e.getMessage());
        }

    }


    private void deleteDoctor(String id, HttpServletRequest request) throws ServiceException {

        ServiceFactory factory = new ServiceFactory();

        DoctorService service = factory.getService(DoctorService.class);

        Doctor doctor;
        doctor = service.findByIdentity(Integer.parseInt(id));
        if (doctor != null) {
            service.delete(doctor.getIdentity());
        }

    }


    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.doctor"), false);
    }
}
