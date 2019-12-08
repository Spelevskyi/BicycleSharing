package by.epam.project.action.command.user;

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
import by.epam.project.logic.user.EnableCardLogic;
import by.epam.project.util.Constants;
import by.epam.project.util.PageError;

public class EnableCardCommand implements ActionCommand {

    public static final Logger logger = LogManager.getLogger(EnableCardCommand.class);

    private Logic logic = new EnableCardLogic();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Enabling credit card executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        String cardId = request.getParameter(Constants.ENABLE_CARD_ID);
        List<String> parameters = new ArrayList<>();
        parameters.add(cardId);
        try {
            logic.action(parameters);
            router.setRoutePath(RoutePath.REDIRECT_USER_BILLING.getRoutePath());
            logger.info("Successfully enabling credit card executing!");
        } catch (LogicException ex) {
            logger.info(ex);
            request.setAttribute(Constants.ERROR, PageError.getError(Constants.TRUE, ex.getMessage()));
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}

