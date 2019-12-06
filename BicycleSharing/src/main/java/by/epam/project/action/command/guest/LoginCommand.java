package by.epam.project.action.command.guest;

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
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.logic.guest.LoginLogic;
import by.epam.project.util.Constants;
import by.epam.project.util.PageError;

public class LoginCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private Logic logic = new LoginLogic();

    public Router execute(HttpServletRequest request) {
        logger.info("Guest login executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        HttpSession session = request.getSession(true);
        String email = request.getParameter(Constants.EMAIL);
        String password = request.getParameter(Constants.PASSWORD);
        List<String> parameters = new ArrayList<String>();
        parameters.add(email);
        parameters.add(password);
        try {
            logic.action(parameters);
            LoginLogic loginLogic = (LoginLogic) logic;
            session.setAttribute(Constants.SESSION_USER, loginLogic.getUser());
            session.setAttribute(Constants.SESSION_ROLE, loginLogic.getUser().getRole());
            session.setAttribute(Constants.SESSION_IS_LOGIN, Constants.TRUE);
            if (loginLogic.getUser().getRole().equals(RoleType.ADMIN)) {
                router.setRoutePath(RoutePath.REDIRECT_ADMIN_HOME.getRoutePath());
                logger.info("Succesfully admin log in system.");
            } else {
                router.setRoutePath(RoutePath.REDIRECT_USER_HOME.getRoutePath());
                logger.info("Succesfully user log in system.");
            }
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, PageError.getError(Constants.TRUE, ex.getMessage()));
            router.setRoutePath(RoutePath.INDEX_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
