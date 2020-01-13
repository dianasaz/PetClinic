package by.sazanchuk.finalTask.controller.command.action.service;

import by.sazanchuk.finalTask.controller.command.ConfigurationManager;
import by.sazanchuk.finalTask.controller.command.action.Command;
import by.sazanchuk.finalTask.controller.command.action.CommandResult;
import by.sazanchuk.finalTask.entity.Service;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import by.sazanchuk.finalTask.service.ServiceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Delete service command.
 */
public class DeleteServiceCommand implements Command {
    private static final String NAME = "name";
    private static final String USER_ID = "user_id";
    private static final String ERROR_DELETE = "error_delete";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter(NAME);

        try {
        if (name != null | !name.isEmpty()) {
            deleteService(name, request);
            return new CommandResult("/controller?command=watch_service", false);
        } else {
            request.setAttribute(ERROR_DELETE, true);
            return goBackWithError(request, "can't delete service");
        }
    } catch (ServiceException e) {
        return goBackWithError(request, e.getMessage());
    }

}


    private void deleteService(String name, HttpServletRequest request) throws ServiceException {

            ServiceFactory factory = new ServiceFactory();

            ServiceService service = factory.getService(ServiceService.class);

            Service service1;
            service1 = service.searchServiceByName(name);
            if (service1 != null) {
                service.delete(service1.getIdentity());
            }

    }


    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.service"), false);
    }
}



