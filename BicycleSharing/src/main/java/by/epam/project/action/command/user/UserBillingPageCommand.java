package by.epam.project.action.command.user;

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
import by.epam.project.entity.user.User;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.logic.user.BillingUserLogic;
import by.epam.project.util.Constants;

public class UserBillingPageCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(UserBillingPageCommand.class);

    private Logic logic = new BillingUserLogic();

    /**
     * Command of forwarding to user billing page
     */
    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("User billing page forwarding executing.");
        Router router = new Router();
        router.setType(RouteType.FORWARD);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        List<String> parameters = new ArrayList<>();
        parameters.add(String.valueOf(user.getId()));
        try {
            logic.action(parameters);
            BillingUserLogic billingLogic = (BillingUserLogic) logic;
            request.setAttribute(Constants.DEBTS, billingLogic.getDebets());
            request.setAttribute(Constants.CARDS, billingLogic.getCards());
            router.setRoutePath(RoutePath.USER_BILLING_PAGE_PATH.getRoutePath());
            logger.info("Forwarding to user billing page.");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, ex.getMessage());
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
