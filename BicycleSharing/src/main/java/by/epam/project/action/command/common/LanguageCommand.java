package by.epam.project.action.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.action.command.ActionCommand;
import by.epam.project.action.command.RouteType;
import by.epam.project.action.command.Router;
import by.epam.project.util.Constants;

public class LanguageCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(LanguageCommand.class);

    /**
     * Command for changing language
     */
    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Changing language executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        HttpSession session = request.getSession();
        String previousPage = (String) session.getAttribute(Constants.PREVIOUS_PATH_PAGE);
        String language = request.getParameter(Constants.LANGUAGE);
        session.setAttribute(Constants.LANGUAGE, language);
        router.setRoutePath(previousPage);
        logger.error("Succesfully changing language executing!");
        return router;
    }
}
