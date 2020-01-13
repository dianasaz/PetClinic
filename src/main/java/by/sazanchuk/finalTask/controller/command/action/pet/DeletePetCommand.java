package by.sazanchuk.finalTask.controller.command.action.pet;

import by.sazanchuk.finalTask.controller.command.ConfigurationManager;
import by.sazanchuk.finalTask.controller.command.action.Command;
import by.sazanchuk.finalTask.controller.command.action.CommandResult;
import by.sazanchuk.finalTask.entity.Pet;
import by.sazanchuk.finalTask.service.PetService;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Delete pet command.
 */
public class DeletePetCommand implements Command {
    private static final String NAME = "name";
    private static final String USER_ID = "user_id";
    private static final String ERROR_DELETE = "error_delete";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter(NAME);

        try {
            if (name == null || name.isEmpty()) {
                request.setAttribute(ERROR_DELETE, true);
                return goBackWithError(request, "can't delete pet");
               } else {
                deletePet(name, request);
                return new CommandResult("/controller?command=profile_user", false);
            }
        } catch (ServiceException e) {
            return goBackWithError(request, "ERROR");
        }

    }


    private void deletePet(String name, HttpServletRequest request) throws ServiceException {
        Integer user_id = (Integer) request.getSession().getAttribute(USER_ID);
        if (user_id != null) {
            ServiceFactory factory = new ServiceFactory();
            PetService service = factory.getService(PetService.class);
            Pet pet;
            pet = service.findByNameAndUserId(name, user_id);
            if (pet != null) {
                service.delete(pet.getIdentity());
            }
        }
    }


    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.profile_user"), false);
    }
}


