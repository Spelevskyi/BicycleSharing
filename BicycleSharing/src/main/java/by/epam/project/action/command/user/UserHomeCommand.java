package by.epam.project.action.command.user;

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
import by.epam.project.entity.user.User;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.logic.user.UserHomeLogic;
import by.epam.project.util.Constants;

public class UserHomeCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(UserHomeCommand.class);
    private Logic logic = new UserHomeLogic();

    public Router execute(HttpServletRequest request) {
        logger.info("User home page forwarding executing.");
        Router router = new Router();
        router.setType(RouteType.FORWARD);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        List<String> parameters = new ArrayList<String>();
        parameters.add(String.valueOf(user.getId()));
        try {
            logic.action(parameters);
            UserHomeLogic homeLogic = (UserHomeLogic) logic;
            request.setAttribute(Constants.ORDER_LIST, homeLogic.getOrders());
            request.setAttribute(Constants.ACTIVE_ORDER, homeLogic.getActiveOrder());
            router.setRoutePath(RoutePath.USER_MAIN_PAGE_PATH.getRoutePath());
            logger.info("Succesfully forwarding to user home page.");
        } catch (LogicException ex) {
            logger.error(ex);
            router.setRoutePath(session.getAttribute(Constants.PREVIOUS_PATH_PAGE).toString());
        }
        return router;
    }
}
