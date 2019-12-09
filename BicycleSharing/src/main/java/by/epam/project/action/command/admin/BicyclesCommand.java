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
import by.epam.project.logic.admin.BicyclesLogic;
import by.epam.project.util.Constants;

public class BicyclesCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(BicyclesCommand.class);

    private Logic logic = new BicyclesLogic();

    /**
     * Command of forwarding to bicycles page
     */
    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Bicycles page forwarding command executing.");
        Router router = new Router();
        router.setType(RouteType.FORWARD);
        List<String> parameters = new ArrayList<>();
        try {
            logic.action(parameters);
            BicyclesLogic bicyclesLogic = (BicyclesLogic) logic;
            request.setAttribute(Constants.BICYCLES, bicyclesLogic.getBicycles());
            request.setAttribute(Constants.PRICE_LIST, bicyclesLogic.getLists());
            router.setRoutePath(RoutePath.BICYCLES_PAGE_PATH.getRoutePath());
            logger.info("Succesfully forwarding to bicycles page executing!");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, ex.getMessage());
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
