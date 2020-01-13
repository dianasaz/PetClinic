package by.sazanchuk.finalTask.controller.command.action.service;

import by.sazanchuk.finalTask.controller.command.ConfigurationManager;
import by.sazanchuk.finalTask.controller.command.action.Command;
import by.sazanchuk.finalTask.controller.command.action.CommandResult;
import by.sazanchuk.finalTask.controller.command.action.authorization.RegisterCommand;
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
import java.util.HashMap;
import java.util.Map;

/**
 * The type Edit service command.
 */
public class EditServiceCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RegisterCommand.class);

    private static final String PRICE = "price";
    private static final String NAME = "name";
    private static final String VALID = "valid";
    private static final String ID = "id";
    private static final String SERVICE = "service";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        Service oldService = (Service) request.getSession().getAttribute(SERVICE);
        String service_id = request.getParameter(ID);
        Service s = null;
        Map<String, String> parameters = new HashMap<>();
        parameters.put(NAME, request.getParameter(NAME));
        parameters.put(PRICE, request.getParameter(PRICE));
        if (oldService == null) {
            try {
                s = searchService(service_id);
            } catch (ServiceException e) {
                logger.log(Level.INFO, e.getMessage());
                return goBackWithError(request, e.getMessage());
            }
        }

        boolean b = checkChanges(parameters);
        if (b) {
            try {
                Map<String, String> oldParam = new HashMap<>();
                oldParam.put(NAME, oldService.getName());
                oldParam.put(PRICE, String.valueOf(oldService.getPrice()));
                oldParam.put(ID, String.valueOf(oldService.getIdentity()));

                Service service = new Service();
                if (parameters.get(NAME) == null || parameters.get(NAME).isEmpty()) {
                    service.setName(oldParam.get(NAME));
                } else service.setName(parameters.get(NAME));
                if (parameters.get(PRICE) == null || parameters.get(PRICE).isEmpty()) {
                    service.setPrice(Integer.valueOf(oldParam.get(PRICE)));
                } else service.setPrice(Integer.valueOf(parameters.get(PRICE)));
                service.setIdentity(Integer.valueOf(oldParam.get(ID)));

                ServiceFactory factory = new ServiceFactory();
                ServiceService serviceS = factory.getService(ServiceService.class);

                if (isValid(service).equals(VALID)) {
                    serviceS.save(service);
                } else return goBackWithError(request, isValid(service));

                request.getSession().removeAttribute(SERVICE);
                request.getSession().removeAttribute(SERVICE);
                return new CommandResult("controller?command=watch_service", true);
            } catch (ServiceException e) {
                logger.log(Level.INFO, e.getMessage());
                return goBackWithError(request, e.getMessage());
            }
        } else {
            request.getSession().setAttribute(SERVICE, s);
            return goBackWithError(request, "error update");
        }

    }

    private boolean checkChanges(Map<String, String> param) {
        for (Map.Entry<String, String> entry : param.entrySet()) {
            if (entry.getValue() != null) {
                return true;
            }
        }
        return false;
    }

    private String isValid(Service service) {
        ServiceValidator serviceValidator = ServiceValidator.getValidator();
        return serviceValidator.isValid(service);
    }

    private Service searchService(String id) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        ServiceService service = factory.getService(ServiceService.class);
        return service.findByIdentity(Integer.valueOf(id));
    }


    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.edit_service"), false);
    }
}
