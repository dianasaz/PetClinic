package by.sazanchuk.finalTask.controller.command.action.service;

import by.sazanchuk.finalTask.controller.command.ConfigurationManager;
import by.sazanchuk.finalTask.controller.command.action.Command;
import by.sazanchuk.finalTask.controller.command.action.CommandResult;
import by.sazanchuk.finalTask.entity.Service;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import by.sazanchuk.finalTask.service.ServiceService;
import by.sazanchuk.finalTask.validator.ServiceValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Add service command.
 */
public class AddServiceCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddServiceCommand.class);
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String ERROR_NULL = "error_null";
    private static final String VALID = "valid";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(NAME);
        String price = request.getParameter(PRICE);

        if (name == null || name.isEmpty() || price == null || price.isEmpty()) {
            logger.log(Level.INFO, "name or price is null");
            return goBackWithError(request, ERROR_NULL);
        } else {
            try {
                if (!searchService(name)) {
                    ServiceFactory factory = new ServiceFactory();
                    ServiceService service = factory.getService(ServiceService.class);
                    Integer pr = Integer.valueOf(price);
                    Service s = new Service();
                    s.setName(name);
                    s.setPrice(pr);

                    if (isValid(s).equals(VALID)) {
                        service.save(s);
                        return new CommandResult("/controller?command=watch_service", false); //TODO
                    } else return goBackWithError(request, isValid(s));
                } else {
                    return goBackWithError(request, ERROR_NULL);
                }
            } catch (ServiceException e) {
                logger.log(Level.INFO, e.getMessage());
                return goBackWithError(request, e.getMessage());
            }
        }
    }


    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.add_service"), false);
    }

    private String isValid(Service service) {
        ServiceValidator serviceValidator = ServiceValidator.getValidator();
        return serviceValidator.isValid(service);
    }

    private boolean searchService(String name) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        ServiceService service = factory.getService(ServiceService.class);

        return service.searchService(name);
    }
}
