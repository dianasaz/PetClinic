package by.sazanchuk.finalTask.controller.command.action.pet;

import by.sazanchuk.finalTask.controller.command.ConfigurationManager;
import by.sazanchuk.finalTask.controller.command.action.Command;
import by.sazanchuk.finalTask.controller.command.action.CommandResult;
import by.sazanchuk.finalTask.entity.Pet;
import by.sazanchuk.finalTask.entity.PetList;
import by.sazanchuk.finalTask.service.PetService;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import by.sazanchuk.finalTask.validator.PetValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The type Register pet command.
 */
public class RegisterPetCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RegisterPetCommand.class);

    private static final String NAME = "name";
    private static final String USER_ID = "user_id";
    private static final String KIND = "kind";
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String VALID = "valid";
    private static final String PET = "pet";
    private static final String PETNAME = "petName";
    private static final String ERROR_USER_NULL = "error_user_null";
    private static final String ERROR_INFO_NULL = "error_info_null";
    private static final String ERROR_DATE = "error_date";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(NAME);
        String kind = request.getParameter(KIND);
        String dateOfBirth = request.getParameter(DATE_OF_BIRTH);

        try {
            if (name != null && dateOfBirth != null && kind != null) {
                Integer user_id = (Integer) request.getSession().getAttribute(USER_ID);
                if (user_id != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

                    ServiceFactory factory = new ServiceFactory();

                    PetService service = factory.getService(PetService.class);

                    Pet pet = new Pet();

                    pet.setName(name);
                    pet.setUser_identity(user_id);
                    pet.setKind(PetList.setPet(kind));
                    try {
                        pet.setDateOfBirth(dateFormat.parse(dateOfBirth));
                    } catch (ParseException e) {
                        goBackWithError(request, ERROR_DATE);
                    }
                    if (isValid(pet).equals(VALID)) {
                        int pet_Id = service.save(pet);
                        if (pet_Id != 0) {
                            pet.setIdentity(pet_Id);
                        }
                        setAtributesToSession(pet, request);
                        return new CommandResult("/controller?command=profile_user", false);
                    } else return goBackWithError(request, isValid(pet));
                } else return goBackWithError(request, ERROR_USER_NULL);
            } else return goBackWithError(request, ERROR_INFO_NULL);
        } catch (ServiceException e) {
            return goBackWithError(request, e.getMessage());
        }
    }


    private String isValid(Pet pet) {
        PetValidator petValidator = PetValidator.getValidator();
        return petValidator.isValid(pet);
    }

    private void setAtributesToSession(Pet pet, HttpServletRequest request) {
        request.setAttribute(PET, pet);
        request.setAttribute(PETNAME, pet.getName());
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.register_pet"), false);
    }
}
