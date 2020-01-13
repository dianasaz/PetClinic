package by.sazanchuk.finalTask.controller.command.action.search;

import by.sazanchuk.finalTask.controller.command.ConfigurationManager;
import by.sazanchuk.finalTask.controller.command.action.Command;
import by.sazanchuk.finalTask.controller.command.action.CommandResult;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.Service;
import by.sazanchuk.finalTask.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SearchCommand implements Command {
    private static final String SEARCH = "search";
    private static final String ERROR_NULL = "error_null_search";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String search = request.getParameter(SEARCH);

        try {
            if (search != null) {
                List<Object> objects = getResult(search);
                if (objects.size() == 0) {
                    return goBackWithError(request, ERROR_NULL);
                } else {
                    request.setAttribute("objects", objects);
                    return new CommandResult(ConfigurationManager.getProperty("path.page.search"), false);
                }
            } else return goBackWithError(request, "error");
        } catch (ServiceException e) {
            return goBackWithError(request, "error");
        }
    }

    private List<Object> getResult(String search) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        ServiceService service = factory.getService(ServiceService.class);
        DoctorService doctorService = factory.getService(DoctorService.class);

        List<Object> objects = new ArrayList<>();
        List<Doctor> doctors = doctorService.findByName(search);
        List<Service> services = null;

        try {
            int s = Integer.parseInt(search);
            if (s != 0) {
                services = service.searchServiceByPrice(s);
            }
        } catch (NumberFormatException e) {}

        List<Service> se = service.searchServiceByPartOfName(search);
        if (doctors != null) objects.addAll(doctors);
        if (services != null) objects.addAll(services);
        if (se != null) objects.addAll(se);

        return objects;
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.search"), false);
    }
}
