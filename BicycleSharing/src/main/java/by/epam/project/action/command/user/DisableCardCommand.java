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
import by.epam.project.logic.user.DisableCardLogic;
import by.epam.project.util.Constants;

public class DisableCardCommand implements ActionCommand {

    public static final Logger logger = LogManager.getLogger(DisableCardCommand.class);

    private Logic logic = new DisableCardLogic();

    /**
     * Disabling credit card
     */
    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Disabling user card executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        String cardId = request.getParameter(Constants.DISABLE_CARD_ID);
        List<String> parameters = new ArrayList<>();
        parameters.add(cardId);
        try {
            logic.action(parameters);
            router.setRoutePath(RoutePath.REDIRECT_USER_BILLING.getRoutePath());
            logger.info("Successfully disabling credit card executing!");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, ex.getMessage());
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
