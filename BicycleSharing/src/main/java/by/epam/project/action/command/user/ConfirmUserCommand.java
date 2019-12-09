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
import by.epam.project.logic.user.ConfirmUserLogic;
import by.epam.project.util.Constants;

public class ConfirmUserCommand implements ActionCommand {

    public static final Logger logger = LogManager.getLogger(ConfirmUserCommand.class);

    private Logic logic = new ConfirmUserLogic();

    /**
     * Command for confirming user
     */
    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("User confirmation executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        int confirmationCode = (int) session.getAttribute(Constants.CONFIRMATION_CODE);
        int enteredCode = Integer.valueOf(request.getParameter(Constants.CONFIRMATION_CODE));
        List<String> parameters = new ArrayList<>();
        parameters.add(String.valueOf(user.getId()));
        parameters.add(String.valueOf(enteredCode));
        parameters.add(String.valueOf(confirmationCode));
        try {
            logic.action(parameters);
            ConfirmUserLogic confirmLogic = (ConfirmUserLogic) logic;
            request.getSession().setAttribute(Constants.SESSION_USER, confirmLogic.getUser());
            router.setRoutePath(RoutePath.REDIRECT_ACCOUNT_PAGE.getRoutePath());
            logger.info("Successfully confirm user email!");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, ex.getMessage());
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
