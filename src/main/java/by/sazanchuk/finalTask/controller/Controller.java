package by.sazanchuk.finalTask.controller;

import by.sazanchuk.finalTask.controller.command.action.Command;
import by.sazanchuk.finalTask.controller.command.action.factory.CommandFactory;
import by.sazanchuk.finalTask.controller.command.action.CommandResult;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPool;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Controller.
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final String COMMAND = "command";

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroy();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter(COMMAND);
        Command action = CommandFactory.create(command);

        CommandResult result = action.execute(request, response);

        if (result == null) {
            result = new CommandResult("/jsp/error.jsp", false);
        }

        String page = result.getPage();
        if (result.isRedirect()) {
            redirect(response, page);
        } else {
            dispatch(request, response, page);
        }


    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    private void redirect(HttpServletResponse response, String page) throws IOException {
        response.sendRedirect(page);
    }
}