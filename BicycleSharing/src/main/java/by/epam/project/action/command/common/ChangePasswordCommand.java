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
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.logic.common.ChangePasswordLogic;
import by.epam.project.util.Constants;
import by.epam.project.util.PageError;

public class ChangePasswordCommand implements ActionCommand {

    public static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);

    private Logic logic = new ChangePasswordLogic();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Password changing executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(Constants.SESSION_USER);
        String firstPassword = request.getParameter(Constants.FIRST_PASSWORD);
        String secondPassword = request.getParameter(Constants.SECOND_PASSWORD);
        List<String> parameters = new ArrayList<>();
        parameters.add(String.valueOf(userId));
        parameters.add(firstPassword);
        parameters.add(secondPassword);
        try {
            logic.action(parameters);
            router.setRoutePath(RoutePath.REDIRECT_ADMIN_ACCOUNT.getRoutePath());
            logger.info("Successfully changing password.");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, PageError.getError(Constants.TRUE, ex.getMessage()));
        }
        return router;
    }

}

