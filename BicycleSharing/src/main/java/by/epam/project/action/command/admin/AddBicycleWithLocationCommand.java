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
import by.epam.project.logic.admin.AddBicycleWithLocationLogic;
import by.epam.project.util.Constants;

public class AddBicycleWithLocationCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(AddBicycleWithLocationLogic.class);

    private Logic logic = new AddBicycleWithLocationLogic();

    /**
     * Adding bicycle with map point command
     */
    public Router execute(HttpServletRequest request) {
        logger.info("Adding bicycle with location executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        String bicycleId = request.getParameter(Constants.BICYCLE_SELECT_ID);
        String x_coordinate = request.getParameter(Constants.X_COORD);
        String y_coordinate = request.getParameter(Constants.Y_COORD);
        List<String> parameters = new ArrayList<>();
        parameters.add(bicycleId);
        parameters.add(x_coordinate);
        parameters.add(y_coordinate);
        try {
            logic.action(parameters);
            router.setRoutePath(RoutePath.REDIRECT_ADMIN_POINTS.getRoutePath());
            logger.info("Succesfully adding bicycle on map executing!");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, ex.getMessage());
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }

}