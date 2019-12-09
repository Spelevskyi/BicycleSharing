package by.epam.project.action.command.admin;

import java.util.Arrays;
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
import by.epam.project.logic.admin.DeleteUserLogic;
import by.epam.project.util.Constants;

public class DeleteUserCommand implements ActionCommand {

    public static final Logger logger = LogManager.getLogger(DeleteUserCommand.class);

    private Logic logic = new DeleteUserLogic();

    /**
     * Command for deleting users
     */
    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Deleting users executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        String[] requestParameters = request.getParameterValues(Constants.DELETE_BICYCLE_ID);
        try {
            if (requestParameters == null) {
                logger.error("No users choosed for deleting!");
                throw new LogicException("No users choosed for deleting!");
            }
            List<String> parameters = Arrays.asList(requestParameters);
            logic.action(parameters);
            router.setRoutePath(RoutePath.REDIRECT_USERS_PAGE.getRoutePath());
            logger.info("Successfully deleting user or users!");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, ex.getMessage());
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }

}