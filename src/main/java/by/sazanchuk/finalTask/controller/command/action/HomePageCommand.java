package by.sazanchuk.finalTask.controller.command.action;

import by.sazanchuk.finalTask.controller.command.Page;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.Pet;
import by.sazanchuk.finalTask.entity.Service;
import by.sazanchuk.finalTask.entity.User;
import by.sazanchuk.finalTask.service.*;
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
 * The type Home page command.
 */
public class HomePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(HomePageCommand.class);

    private static final String SERVICES = "services";
    private static final String SERVICE_NAMES = "serviceNames";
    private static final String DOCTORS = "doctors";
    private static final String LANGUAGE = "lang";
    private static final String NEXT_LANGUAGE = "nextLang";
    private static final String USER_ID = "user_id";
    private static final String USER_ROLE = "user_role";
    private static final String USER = "user";
    private static final String PETS = "pets";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        List<Service> services = null;
        List<Doctor> doctors = null;
        String[] serviceNames = null;
        try {
            services = getAllService();
            doctors = getAllDoctors();
            serviceNames = new String[services.size()];
            for (int i = 0; i < services.size(); i++){
                serviceNames[i] = services.get(i).getName();
            }

        } catch (ServiceException e) {
            logger.log(Level.INFO, e.getMessage());
            return new CommandResult(Page.HOME_PAGE.getPage(), false);
        }

        String language = "RU";
        String next_lang = "EN";
        String login = null;
        String password = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equalsIgnoreCase(LANGUAGE)) {
                    language = cookies[i].getValue();
                    if (language.equalsIgnoreCase("en")) next_lang = "RU";
                    else next_lang = "EN";
                }
                if (cookies[i].getName().equalsIgnoreCase("user_login")) {
                    if (cookies[i].getValue() != null) login = cookies[i].getValue();
                }
                if (cookies[i].getName().equalsIgnoreCase("user_password")) {
                    if (cookies[i].getValue() != null) password = cookies[i].getValue();
                }
            }
        }

        if (login != null || password != null){
            try {
               initializeUser(login, password, request);
            } catch (ServiceException e) {}
        }
        setAttributesToSession(services, doctors, serviceNames, language, next_lang, request);

        return new CommandResult(Page.HOME_PAGE.getPage(), false);
    }

    private List<Service> getAllService() throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        ServiceService service = factory.getService(ServiceService.class);
        List<Service> services = service.findAll();
        return services;
    }

    private List<Doctor> getAllDoctors() throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        DoctorService service = factory.getService(DoctorService.class);
        List<Doctor> doctors = service.findAll();
        return doctors;
    }


    private void initializeUser(String login, String password, HttpServletRequest request) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        UserService service = factory.getService(UserService.class);
        User user = service.findByLoginAndPassword(login, password);
        if (user != null && user.getId() != null) {
            List<Pet> pets = new ArrayList<>();
            pets = getPets(user.getId());
            setAttributesToSession(user, pets, request);
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


    private void setAttributesToSession(List<Service> services, List<Doctor> doctors, String[] names,String lang, String next_l, HttpServletRequest request) {        HttpSession session = request.getSession();
        session.setAttribute(SERVICES, services);
        session.setAttribute(SERVICE_NAMES, names);
        session.setAttribute(DOCTORS, doctors);
        session.setAttribute(NEXT_LANGUAGE, next_l);
        session.setAttribute(LANGUAGE, lang);
    }

}
