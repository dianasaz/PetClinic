package by.sazanchuk.finalTask.controller.command.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interface Command.
 */
public interface Command {
    /**
     * Execute command result.
     *
     * @param request  the request
     * @param response the response
     * @return the command result
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response);
}
