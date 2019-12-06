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
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.logic.guest.RegistrationLogic;
import by.epam.project.util.Constants;
import by.epam.project.util.PageError;

public class RegisterCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(RegisterCommand.class);

    private Logic logic = new RegistrationLogic();

    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        HttpSession session = request.getSession();
        String firstName = request.getParameter(Constants.FIRST_NAME);
        String lastName = request.getParameter(Constants.LAST_NAME);
        String email = request.getParameter(Constants.EMAIL);
        String firstPassword = request.getParameter(Constants.FIRST_PASSWORD);
        String secondPassword = request.getParameter(Constants.SECOND_PASSWORD);
        String phoneNumber = request.getParameter(Constants.PHONE_NUMBER);
        List<String> parameters = new ArrayList<>();
        parameters.add(firstName);
        parameters.add(lastName);
        parameters.add(email);
        parameters.add(firstPassword);
        parameters.add(secondPassword);
        parameters.add(phoneNumber);
        try {
            logic.action(parameters);
            RegistrationLogic registerLogic = (RegistrationLogic) logic;
            int confirmCode = registerLogic.getConfirmCode();
            request.setAttribute(Constants.CONFIRMATION_CODE, confirmCode);
            request.setAttribute(Constants.EMAIL, email);
            router.setRoutePath(RoutePath.CONFIRMATION_PAGE_PATH.getRoutePath());
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, PageError.getError(Constants.TRUE, ex.getMessage()));
            router.setRoutePath(RoutePath.INDEX_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
