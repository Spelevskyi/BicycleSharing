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
import by.epam.project.logic.user.SendMailLogic;
import by.epam.project.util.Constants;
import by.epam.project.util.PageError;

public class SendMailCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(SendMailCommand.class);

    private Logic logic = new SendMailLogic();

    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setType(RouteType.FORWARD);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        List<String> parameters = new ArrayList<>();
        parameters.add(String.valueOf(user.getId()));
        try {
            logic.action(parameters);
            SendMailLogic mailLogic = (SendMailLogic) logic;
            request.getSession().setAttribute(Constants.CONFIRMATION_CODE, mailLogic.getConfirmCode());
            router.setRoutePath(RoutePath.CONFIRMATION_USER_PAGE_PATH.getRoutePath());
            logger.info("Sending confirmation code to user email!");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, PageError.getError(Constants.TRUE, ex.getMessage()));
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.REDIRECT);
        }
        return router;
    }
}

