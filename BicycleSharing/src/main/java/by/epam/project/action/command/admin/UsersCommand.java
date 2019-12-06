package by.epam.project.action.command.admin;

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
import by.epam.project.logic.admin.UsersLogic;
import by.epam.project.util.Constants;

public class UsersCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(UsersCommand.class);
    private Logic logic = new UsersLogic();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Users page forwardig execitong.");
        Router router = new Router();
        router.setType(RouteType.FORWARD);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        String previousPage = (String) session.getAttribute(Constants.PREVIOUS_PATH_PAGE);
        List<String> parameters = new ArrayList<>();
        parameters.add(String.valueOf(user.getId()));
        try {
            logic.action(parameters);
            UsersLogic usersLogic = (UsersLogic) logic;
            request.setAttribute(Constants.USERS, usersLogic.getUsers());
            router.setRoutePath(RoutePath.USER_INFO_PAGE_PATH.getRoutePath());
            logger.info("Succesfully forwarding to users page!");
        } catch (LogicException ex) {
            logger.error(ex);
            router.setRoutePath(previousPage);
        }
        return router;
    }
}
