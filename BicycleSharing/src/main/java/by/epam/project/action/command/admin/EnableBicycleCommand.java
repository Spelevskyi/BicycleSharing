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
import by.epam.project.logic.admin.EnableBicycleLogic;
import by.epam.project.util.Constants;
import by.epam.project.util.PageError;

public class EnableBicycleCommand implements ActionCommand {

    public static final Logger logger = LogManager.getLogger(EnableBicycleCommand.class);

    private Logic logic = new EnableBicycleLogic();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Enabling bicycle executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        String bicycleId = request.getParameter(Constants.ENABLE_BICYCLE_ID);
        List<String> parameters = new ArrayList<>();
        parameters.add(bicycleId);
        router.setRoutePath(RoutePath.REDIRECT_BICYCLE_PAGE.getRoutePath());
        try {
            logic.action(parameters);
            request.getSession().setAttribute(Constants.ERROR, PageError.getError(Constants.FALSE, ""));
            logger.info("Successfully enabling bicycle.");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, PageError.getError(Constants.TRUE, ex.getMessage()));
        }
        return router;
    }
}
