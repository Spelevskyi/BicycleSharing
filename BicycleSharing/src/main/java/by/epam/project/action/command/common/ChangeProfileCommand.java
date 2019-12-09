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
import by.epam.project.logic.common.ChangeProfileLogic;
import by.epam.project.util.Constants;

public class ChangeProfileCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(ChangeProfileCommand.class);

    private Logic logic = new ChangeProfileLogic();

    /**
     * Command for changing profile info
     */
    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Profile info changing executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        String firstName = request.getParameter(Constants.FIRST_NAME);
        String lastName = request.getParameter(Constants.LAST_NAME);
        String phoneNumber = request.getParameter(Constants.PHONE_NUMBER);
        List<String> parameters = new ArrayList<>();
        parameters.add(firstName);
        parameters.add(lastName);
        parameters.add(phoneNumber);
        parameters.add(String.valueOf(user.getId()));
        try {
            logic.action(parameters);
            ChangeProfileLogic profileLogic = (ChangeProfileLogic) logic;
            request.getSession().setAttribute(Constants.SESSION_USER, profileLogic.getUser());
            router.setRoutePath(RoutePath.REDIRECT_ACCOUNT_PAGE.getRoutePath());
            router.setType(RouteType.REDIRECT);
            logger.info("Succesfully changing user info executing!");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, ex.getMessage());
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
