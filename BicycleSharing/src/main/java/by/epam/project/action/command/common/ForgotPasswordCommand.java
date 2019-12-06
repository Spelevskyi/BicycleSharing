package by.epam.project.action.command.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.action.command.ActionCommand;
import by.epam.project.action.command.RoutePath;
import by.epam.project.action.command.RouteType;
import by.epam.project.action.command.Router;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.logic.common.ForgotPasswordLogic;
import by.epam.project.util.Constants;
import by.epam.project.util.PageError;

public class ForgotPasswordCommand implements ActionCommand {

    public static final Logger logger = LogManager.getLogger(ForgotPasswordCommand.class);

    private Logic logic = new ForgotPasswordLogic();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Password changing executing");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        String email = request.getParameter(Constants.EMAIL);
        String firstPassword = request.getParameter(Constants.FIRST_PASSWORD);
        String secondPassword = request.getParameter(Constants.SECOND_PASSWORD);
        List<String> parameters = new ArrayList<>();
        parameters.add(email);
        parameters.add(firstPassword);
        parameters.add(secondPassword);
        router.setRoutePath(RoutePath.INDEX_PAGE_PATH.getRoutePath());
        try {
            logic.action(parameters);
            request.getSession().setAttribute(Constants.ERROR, PageError.getError(Constants.FALSE, ""));
            logger.info("Successfully changing password.");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, PageError.getError(Constants.TRUE, ex.getMessage()));
            router.setType(RouteType.FORWARD);
        }
        return router;
    }

}
