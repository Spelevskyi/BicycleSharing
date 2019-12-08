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
import by.epam.project.logic.common.ConfirmLogic;
import by.epam.project.util.Constants;
import by.epam.project.util.PageError;

public class ConfirmCommand implements ActionCommand {

    public static final Logger logger = LogManager.getLogger(ConfirmCommand.class);

    private Logic logic = new ConfirmLogic();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("User confirmation executing.");
        Router router = new Router();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute(Constants.EMAIL);
        int confirmationCode = (int) session.getAttribute(Constants.CONFIRMATION_CODE);
        int enteredCode = Integer.valueOf(request.getParameter(Constants.CONFIRMATION_CODE));
        List<String> parameters = new ArrayList<>();
        parameters.add(String.valueOf(email));
        parameters.add(String.valueOf(enteredCode));
        parameters.add(String.valueOf(confirmationCode));
        try {
            logic.action(parameters);
            router.setRoutePath(RoutePath.INDEX_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
            logger.info("Successfully confirm user email!");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, PageError.getError(Constants.TRUE, ex.getMessage()));
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}

