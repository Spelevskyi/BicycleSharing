package by.epam.project.action.command.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.action.command.ActionCommand;
import by.epam.project.action.command.RoutePath;
import by.epam.project.action.command.RouteType;
import by.epam.project.action.command.Router;
import by.epam.project.entity.user.RoleType;
import by.epam.project.entity.user.User;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.logic.common.LogoutLogic;
import by.epam.project.util.Constants;

public class LogoutCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);

    private Logic logic = new LogoutLogic();

    /**
     * Command for log out
     */
    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Logout executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        List<String> parameters = new ArrayList<>();
        parameters.add(String.valueOf(user.getId()));
        try {
            logic.action(parameters);
            session.removeAttribute(Constants.SESSION_USER);
            session.removeAttribute(Constants.PREVIOUS_PATH_PAGE);
            session.removeAttribute(Constants.ERROR);
            session.setAttribute(Constants.SESSION_ROLE, RoleType.GUEST);
            router.setRoutePath(RoutePath.REDIRECT_HOME_PAGE.getRoutePath());
            logger.info("Succesfully logout executing!");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, ex.getMessage());
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
