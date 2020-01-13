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
import java.util.List;

/**
 * The type Watch service command.
 */
public class WatchServiceCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){

        try {
            List<Service> services = getAllService();
            request.setAttribute("services", services);
            return new CommandResult(ConfigurationManager.getProperty("path.page.service"), false);
        } catch (ServiceException e) {
            return goBackWithError(request, "error");
        }
    }

    private List<Service> getAllService() throws ServiceException {

        ServiceFactory factory = new ServiceFactory();

        ServiceService service = factory.getService(ServiceService.class);
        List<Service> services = service.findAll();
        return services;
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.service"), false);
    }

}
