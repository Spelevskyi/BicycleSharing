package by.epam.project.action.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.action.command.ActionCommand;
import by.epam.project.action.command.RoutePath;
import by.epam.project.action.command.RouteType;
import by.epam.project.action.command.Router;
import by.epam.project.entity.user.RoleType;
import by.epam.project.util.Constants;

public class LogoutCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setType(RouteType.FORWARD);
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.SESSION_USER);
        session.removeAttribute(Constants.PREVIOUS_PATH_PAGE);
        session.setAttribute(Constants.SESSION_ROLE, RoleType.GUEST);
        request.getSession().setAttribute(Constants.ERROR, Constants.FALSE);
        router.setRoutePath(RoutePath.INDEX_PAGE_PATH.getRoutePath());
        logger.info("Succesfully logout.");
        return router;
    }
}
