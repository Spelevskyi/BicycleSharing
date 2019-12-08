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
import by.epam.project.logic.admin.AddBicycleLogic;
import by.epam.project.util.Constants;
import by.epam.project.util.PageError;

public class AddBicycleCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(AddBicycleCommand.class);

    private Logic logic = new AddBicycleLogic();

    public Router execute(HttpServletRequest request) {
        logger.info("Adding bicycle executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        String brand = request.getParameter(Constants.ADD_BICYCLE_BRAND);
        String color = request.getParameter(Constants.ADD_BICYCLE_COLOR);
        String speed = request.getParameter(Constants.ADD_BICYCLE_MAX_SPEED);
        String state = request.getParameter(Constants.ADD_BICYCLE_STATE);
        String imagePath = request.getParameter(Constants.ADD_BICYCLE_IMAGE_PATH);
        String status = new String();
        if (request.getParameter(Constants.ADD_BICYCLE_STATUS) == null) {
            status = Constants.DISABLE_VALUE;
        } else {
            status = request.getParameter(Constants.ADD_BICYCLE_STATUS);
        }
        List<String> parameters = new ArrayList<>();
        parameters.add(brand);
        parameters.add(color);
        parameters.add(state);
        parameters.add(status);
        parameters.add(speed);
        parameters.add(imagePath);
        try {
            logic.action(parameters);
            router.setRoutePath(RoutePath.REDIRECT_BICYCLE_PAGE.getRoutePath());
            logger.info("Redirecting to admin points page.");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR,
                    PageError.getError(Constants.TRUE, ex.getMessage()));
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }

}
