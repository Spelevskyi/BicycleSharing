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
import by.epam.project.entity.user.User;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.logic.common.ChangePasswordLogic;
import by.epam.project.util.Constants;

public class ChangePasswordCommand implements ActionCommand {

    public static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);

    private Logic logic = new ChangePasswordLogic();

    /**
     * Command for changing user password
     */
    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Password changing executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        String firstPassword = request.getParameter(Constants.FIRST_PASSWORD);
        String secondPassword = request.getParameter(Constants.SECOND_PASSWORD);
        List<String> parameters = new ArrayList<>();
        parameters.add(String.valueOf(user.getId()));
        parameters.add(firstPassword);
        parameters.add(secondPassword);
        try {
            logic.action(parameters);
            router.setRoutePath(RoutePath.REDIRECT_ACCOUNT_PAGE.getRoutePath());
            logger.info("Successfully admin changing password executing.");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, ex.getMessage());
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }

}
