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
import by.epam.project.logic.admin.DisableBicycleLogic;
import by.epam.project.util.Constants;
import by.epam.project.util.PageError;

public class DisableBicycleCommand implements ActionCommand {

    public static final Logger logger = LogManager.getLogger(DisableBicycleCommand.class);

    private Logic logic = new DisableBicycleLogic();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Disabling bicycle executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        String bicycleId = request.getParameter(Constants.DISABLE_BICYCLE_ID);
        List<String> parameters = new ArrayList<>();
        parameters.add(bicycleId);
        try {
            logic.action(parameters);
            router.setRoutePath(RoutePath.REDIRECT_BICYCLE_PAGE.getRoutePath());
            logger.info("Successfully disabling bicycle.");
        } catch (LogicException ex) {
            logger.error(ex);
            request.setAttribute(Constants.ERROR, PageError.getError(Constants.TRUE, ex.getMessage()));
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
