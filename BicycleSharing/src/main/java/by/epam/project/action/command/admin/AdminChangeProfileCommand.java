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
import by.epam.project.logic.common.ChangeProfileLogic;
import by.epam.project.util.Constants;
import by.epam.project.util.PageError;

public class AdminChangeProfileCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(AdminChangeProfileCommand.class);

    private Logic logic = new ChangeProfileLogic();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Admin profile info changing executing.");
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
            router.setRoutePath(RoutePath.REDIRECT_ADMIN_ACCOUNT.getRoutePath());
            router.setType(RouteType.REDIRECT);
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, PageError.getError(Constants.TRUE, ex.getMessage()));
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;

    }
}
