package by.sazanchuk.finalTask.controller.command.action.profile;

import by.sazanchuk.finalTask.controller.command.ConfigurationManager;
import by.sazanchuk.finalTask.controller.command.action.Command;
import by.sazanchuk.finalTask.controller.command.action.CommandResult;
import by.sazanchuk.finalTask.controller.command.action.authorization.RegisterCommand;
import by.sazanchuk.finalTask.entity.Role;
import by.sazanchuk.finalTask.entity.User;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import by.sazanchuk.finalTask.service.UserService;
import by.sazanchuk.finalTask.validator.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Edit profile command.
 */
public class EditProfileCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RegisterCommand.class);

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EMAIL = "email";
    private static final String ID = "id";
    private static final String USER = "user";
    private static final String ROLE = "role";
    private static final String VALID = "valid";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        User olduser = (User) request.getSession().getAttribute("user");

        Map<String, String> oldParam = new HashMap<>();
        oldParam.put(LOGIN, olduser.getLogin());
        oldParam.put(PASSWORD, olduser.getPassword());
        oldParam.put(NAME, olduser.getName());
        oldParam.put(ADDRESS, olduser.getAddress());
        oldParam.put(PHONE_NUMBER, olduser.getPhoneNumber().toString());
        oldParam.put(EMAIL, olduser.getEmail());
        oldParam.put(ID, olduser.getId().toString());
        oldParam.put(ROLE, olduser.getRole().getName());

        Map<String, String> parameters = new HashMap<>();
        parameters.put(LOGIN, request.getParameter(LOGIN));
        parameters.put(PASSWORD, request.getParameter(PASSWORD));
        parameters.put(NAME, request.getParameter(NAME));
        parameters.put(ADDRESS, request.getParameter(ADDRESS));
        parameters.put(PHONE_NUMBER, request.getParameter(PHONE_NUMBER));
        parameters.put(EMAIL, request.getParameter(EMAIL));

        boolean b = checkChanges(parameters);
        if (b) {
            try {
                if (!checkIfUserExist(parameters.get(LOGIN), oldParam)) {
                    User user = new User();
                    if (parameters.get(LOGIN) == null || parameters.get(LOGIN).isEmpty()) {
                        user.setLogin(oldParam.get(LOGIN));
                    } else user.setLogin(parameters.get(LOGIN));
                    if (parameters.get(PASSWORD) == null || parameters.get(PASSWORD).isEmpty()) {
                        user.setPassword(oldParam.get(PASSWORD));
                    } else user.setPassword(parameters.get(PASSWORD));
                    if (parameters.get(EMAIL) == null || parameters.get(EMAIL).isEmpty()) {
                        user.setEmail(oldParam.get(EMAIL));
                    } else user.setEmail(parameters.get(EMAIL));
                    if (parameters.get(ADDRESS) == null || parameters.get(ADDRESS).isEmpty()) {
                        user.setAddress(oldParam.get(ADDRESS));
                    } else user.setAddress(parameters.get(ADDRESS));
                    if (parameters.get(PHONE_NUMBER) == null || parameters.get(PHONE_NUMBER).isEmpty()) {
                        user.setPhoneNumber(Integer.valueOf(oldParam.get(PHONE_NUMBER)));
                    } else user.setPhoneNumber(Integer.valueOf(parameters.get(PHONE_NUMBER)));
                    if (parameters.get(NAME) == null || parameters.get(NAME).isEmpty()) {
                        user.setName(oldParam.get(NAME));
                    } else user.setName(parameters.get(NAME));
                    user.setId(Integer.valueOf(oldParam.get(ID)));
                    user.setRole(Role.setRole(oldParam.get(ROLE)));

                    ServiceFactory factory = new ServiceFactory();
                    UserService service = factory.getService(UserService.class);
                    if (isValid(user).equals(VALID)) {
                        service.save(user);
                    } else return goBackWithError(request, isValid(user));

                    request.getSession().removeAttribute(USER);
                    request.getSession().setAttribute(USER, user);

                    return new CommandResult("controller?command=profile", true);
                } else return goBackWithError(request, "login exist");
            } catch (ServiceException e) {
                logger.log(Level.INFO, e.getMessage());
                return goBackWithError(request, e.getMessage());
            }
        } else return goBackWithError(request, "error update");
    }

    private boolean checkIfUserExist(String login, Map<String, String> oldParam) throws ServiceException {
        if (login.equals(oldParam.get(LOGIN))) return true;
        ServiceFactory factory = new ServiceFactory();
        UserService service = factory.getService(UserService.class);
        return service.isExist(login);
    }

    private boolean checkChanges(Map<String, String> param) {
        for (Map.Entry<String, String> entry : param.entrySet()) {
            if (entry.getValue() != null) {
                return true;
            }
        }
        return false;
    }

    private String isValid(User user){
        UserValidator userValidator = UserValidator.getValidator();
        return userValidator.isValid(user);
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.edit_profile"), false);
    }
}
