package by.epam.project.action.command.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.action.command.ActionCommand;
import by.epam.project.action.command.RoutePath;
import by.epam.project.action.command.RouteType;
import by.epam.project.action.command.Router;

public class ConfirmPageCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(ConfirmPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Confirm page forwarding executing.");
        Router router = new Router();
        router.setType(RouteType.FORWARD);
        router.setRoutePath(RoutePath.CONFIRMATION_PAGE_PATH.getRoutePath());
        return router;
    }
}