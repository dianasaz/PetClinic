package by.sazanchuk.finalTask.controller.filter;


import by.sazanchuk.finalTask.controller.command.action.access.CommandAccess;
import by.sazanchuk.finalTask.controller.command.action.factory.CommandType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * The type List command filter.
 */
public class ListCommandFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(ListCommandFilter.class);

    private static final String USER_ROLE = "user_role";
    private static final String COMMAND = "command";
    private final static String ERROR_PAGE = "/jsp/error.jsp";
    private final static String NO_ACCESS_PAGE = "/noAccessPage.jsp";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        String userRole = getUserRoleByRequest(httpServletRequest);
        CommandAccess commandAccess = new CommandAccess();
        List<CommandType> commandTypes = commandAccess.getAvailableCommandTypesByUser(userRole);

        String command = servletRequest.getParameter(COMMAND);
        String commandUpper = command.toUpperCase();
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandUpper);
        }catch (IllegalArgumentException exception){
            logger.warn("Incorrect command was input.Command:" + commandUpper);
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(servletRequest, servletResponse);
            return;
        }

        if (commandTypes.contains(commandType)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletRequest) servletRequest).getSession().invalidate();
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher(NO_ACCESS_PAGE);
            requestDispatcher.forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }


    private String getUserRoleByRequest(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String role;
        if (session == null) {
            role = null;
        } else {
            role = (String) session.getAttribute(USER_ROLE);
        }
        return role;
    }
}
