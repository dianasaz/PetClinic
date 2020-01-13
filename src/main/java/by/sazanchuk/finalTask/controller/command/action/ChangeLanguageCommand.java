package by.sazanchuk.finalTask.controller.command.action;

import by.sazanchuk.finalTask.controller.command.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The type Change language command.
 */
public class ChangeLanguageCommand implements Command {
    private static final String LANGUAGE = "lang";
    private static final String EN = "EN";
    private static final String RU = "RU";
    private static final String NEXT_LANGUAGE = "nextLang";
    private static final String REFERER = "referer";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String language = request.getParameter(LANGUAGE).toUpperCase();

        if (language.equalsIgnoreCase(RU)) {
            language = EN;
        } else {
            language = RU;
        }
        String next = getNextLang(language);
        setAttributes(request, language, next);

        response.addCookie(new Cookie("lang", language));

        String referer = request.getHeader(REFERER);
        int pos = referer.lastIndexOf("/") + 1;
        String path;
        if (pos >= referer.length()){
            path = "controller?command=home_page";
        } else {
            path = referer.substring(pos);
        }
        return new CommandResult(path, true);
    }

    /**
     * Sets attributes.
     *
     * @param request the request
     * @param lang    the lang
     * @param next    the next
     */
    public void setAttributes(HttpServletRequest request, String lang, String next) {
        HttpSession session = request.getSession();
        session.setAttribute(LANGUAGE, lang);
        session.setAttribute(NEXT_LANGUAGE, next);
    }

    private String getNextLang(String lang) {
        String next;
        if (lang.equalsIgnoreCase(RU)) {
            next = EN;
        } else {
            next = RU;
        }
        return next;
    }

}
