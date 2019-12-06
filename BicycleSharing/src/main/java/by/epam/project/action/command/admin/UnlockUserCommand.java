package by.epam.project.action.command.admin;

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
import by.epam.project.logic.admin.UnlockUserLogic;
import by.epam.project.util.Constants;
import by.epam.project.util.PageError;

public class UnlockUserCommand implements ActionCommand {

    public static final Logger logger = LogManager.getLogger(UnlockUserCommand.class);

    private Logic logic = new UnlockUserLogic();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Unlocking user executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        String userId = request.getParameter(Constants.UNLOCK_USER_ID);
        List<String> parameters = new ArrayList<>();
        parameters.add(userId);
        router.setRoutePath(RoutePath.REDIRECT_USERS_PAGE.getRoutePath());
        try {
            logic.action(parameters);
            router.setRoutePath(RoutePath.REDIRECT_USERS_PAGE.getRoutePath());
            logger.info("Successfully unlocking user.");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, PageError.getError(Constants.TRUE, ex.getMessage()));
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }


}
