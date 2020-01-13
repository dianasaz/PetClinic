package by.sazanchuk.finalTask.controller.command.action.authorization;

import by.sazanchuk.finalTask.controller.command.ConfigurationManager;
import by.sazanchuk.finalTask.controller.command.action.Command;
import by.sazanchuk.finalTask.controller.command.action.CommandResult;
import by.sazanchuk.finalTask.entity.Pet;
import by.sazanchuk.finalTask.entity.User;
import by.sazanchuk.finalTask.service.PetService;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import by.sazanchuk.finalTask.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Login command.
 */
public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ERROR_LOGIN = "error_login";
    private static final String ERROR_PASSWORD = "error_password";
    private static final String ERROR_AUTHENTIFICATION = "error_authentification";
    private static final String USER_ID = "user_id";
    private static final String USER_ROLE = "user_role";
    private static final String USER = "user";
    private static final String PETS = "pets";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        if (login == null || login.isEmpty()) {
            logger.log(Level.INFO, "invalid login was received");
            return goBackWithError(request, ERROR_LOGIN);
        }
        if (password == null || password.isEmpty()) {
            logger.log(Level.INFO, "invalid password was received");
            return goBackWithError(request, ERROR_PASSWORD);
        }
        boolean userExist = false;
        try {
            userExist = initializeUser(login, password, request);

        } catch (ServiceException e) {
            logger.log(Level.INFO, e.getMessage());
            goBackWithError(request, e.getMessage());
        }

        if (userExist) {
            logger.log(Level.INFO, "user authorized with login - " + login);

            Cookie l = null;
            Cookie p = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c: cookies) {
                    if (c.getName().equalsIgnoreCase("user_login")) {
                        c.setValue(login);
                        response.addCookie(c);
                        l = c;
                    }
                    if (c.getName().equalsIgnoreCase("user_password")) {
                        c.setValue(password);
                        response.addCookie(c);
                        p = c;
                    }
                }
            }

            if (l == null && p == null) {
                response.addCookie(new Cookie("user_login", login));
                response.addCookie(new Cookie("user_password", password));
            }
            return new CommandResult("controller?command=home_page", true);
        } else {
            logger.log(Level.INFO, "user with such login and password doesn't exist");
            return goBackWithError(request, ERROR_AUTHENTIFICATION);
        }
    }

    private boolean initializeUser(String login, String password, HttpServletRequest request) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        UserService service = factory.getService(UserService.class);
        User user = service.findByLoginAndPassword(login, password);
        if (user != null && user.getId() != null) {
            List<Pet> pets = new ArrayList<>();
            pets = getPets(user.getId());
            setAttributesToSession(user, pets, request);
            return true;
        } else {
            return false;
        }
    }

    private List<Pet> getPets(Integer id) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        PetService service = factory.getService(PetService.class);
        List<Pet> pets = new ArrayList<>();
        pets = service.getPetsOfOneUser(id);
        return pets;
    }

    private void setAttributesToSession(User user, List<Pet> pets, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(USER_ID, user.getId());
        session.setAttribute(USER_ROLE, user.getRole().getName());
        session.setAttribute(USER, user);
        request.setAttribute(USER, true);
        session.setAttribute(PETS, pets);
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.login"), false);
    }

}
