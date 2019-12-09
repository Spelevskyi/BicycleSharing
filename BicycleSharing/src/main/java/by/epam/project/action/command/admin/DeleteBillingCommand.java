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
import by.epam.project.logic.admin.DeleteBillingLogic;
import by.epam.project.util.Constants;

public class DeleteBillingCommand implements ActionCommand {

    public static final Logger logger = LogManager.getLogger(DeleteBillingCommand.class);
    private Logic logic = new DeleteBillingLogic();

    /**
     * Command for deleting price list
     */
    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Deleting price list executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        String[] requestParameters = request.getParameterValues(Constants.DELETE_BILLING_ID);
        try {
            if (requestParameters == null) {
                logger.error("No billing choosed for deleting!");
                throw new LogicException("No billing choosed for deleting!");
            }
            List<String> parameters = Arrays.asList(requestParameters);
            logic.action(parameters);
            router.setRoutePath(RoutePath.REDIRECT_ADMIN_BILLING.getRoutePath());
            logger.info("Successfully deleting price list.");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, ex.getMessage());
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}

