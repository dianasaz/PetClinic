package by.sazanchuk.finalTask.controller.command.action.profile;

import by.sazanchuk.finalTask.controller.command.ConfigurationManager;
import by.sazanchuk.finalTask.controller.command.action.Command;
import by.sazanchuk.finalTask.controller.command.action.CommandResult;
import by.sazanchuk.finalTask.controller.command.action.authorization.LoginCommand;
import by.sazanchuk.finalTask.entity.Coupon;
import by.sazanchuk.finalTask.entity.Pet;
import by.sazanchuk.finalTask.entity.User;
import by.sazanchuk.finalTask.service.CouponService;
import by.sazanchuk.finalTask.service.PetService;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import by.sazanchuk.finalTask.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Profile admin command.
 */
public class ProfileAdminCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final String USERS = "users";
    private static final String USER = "user";
    private static final String LOGIN = "login";
    private static final String COUPONS = "coupons";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute(USER);

        try {
            User u = null;
            if (user != null) {
                u = initializeUser(user.getId());
                List<User> users = getAllUsers();
                List<Coupon> coupons = getCoupons();
                setAttributesToSession(u, users, coupons, request);
            }
            if (u != null) return new CommandResult(ConfigurationManager.getProperty("path.page.profile_admin"), false);
            else return goBackWithError(request, "error");
        } catch (ServiceException e) {
            return goBackWithError(request, e.getMessage());
        }
    }

    private User initializeUser(Integer id) throws ServiceException {

        ServiceFactory factory =  new ServiceFactory();
        UserService service = factory.getService(UserService.class);
        User user = service.findByIdentity(id);
        return user;
    }

    private List<User> getAllUsers() throws ServiceException {

        ServiceFactory factory = new ServiceFactory();
        UserService service = factory.getService(UserService.class);
        List<User> users = service.findAll();
        return users;
    }

    private List<Coupon> getCoupons() throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        CouponService service = factory.getService(CouponService.class);
        List<Coupon> coupons = service.findAll();
        return coupons;
    }


    private void setAttributesToSession(User user, List<User> users, List<Coupon> coupons, HttpServletRequest request) {
        request.setAttribute(LOGIN, user.getLogin());
        request.setAttribute(USERS, users);
        request.setAttribute(COUPONS, coupons);
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.login"), false);
    }
}