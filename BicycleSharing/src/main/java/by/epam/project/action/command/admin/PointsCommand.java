package by.epam.project.action.command.admin;

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
import by.epam.project.logic.admin.PointsLogic;
import by.epam.project.util.Constants;

public class PointsCommand implements ActionCommand {

    public static final Logger logger = LogManager.getLogger(PointsCommand.class);
    private Logic logic = new PointsLogic();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Forwarding to points page executing.");
        Router router = new Router();
        router.setType(RouteType.FORWARD);
        HttpSession session = request.getSession();
        String previousPage = (String) session.getAttribute(Constants.PREVIOUS_PATH_PAGE);
        List<String> parameters = new ArrayList<>();
        try {
            logic.action(parameters);
            PointsLogic pointsLogic = (PointsLogic) logic;
            request.setAttribute(Constants.JSON, pointsLogic.getPointsJson());
            request.setAttribute(Constants.NOT_LOCATED_BICYCLES, pointsLogic.getBicycles());
            router.setRoutePath(RoutePath.POINTS_PAGE_PATH.getRoutePath());
            logger.info("Succesfully forwarding to points page executing!");
        } catch (LogicException ex) {
            logger.error(ex);
            router.setRoutePath(previousPage);
        }
        return router;
    }
}
