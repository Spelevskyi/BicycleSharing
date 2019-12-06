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
import by.epam.project.logic.user.ReplenishBalanceLogic;
import by.epam.project.util.Constants;
import by.epam.project.util.PageError;

public class ReplenishBalanceCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(ReplenishBalanceCommand.class);

    private Logic logic = new ReplenishBalanceLogic();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("User balance replenishing executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        String amount = request.getParameter(Constants.REPLENISH_BALANCE_AMOUNT);
        String cardId = request.getParameter(Constants.REPLENISH_BALANCE_ID);
        String code = request.getParameter(Constants.REPLENISH_BALANCE_CODE);
        String number = request.getParameter(Constants.REPLENISH_BALANCE_NUMBER);
        List<String> parameters = new ArrayList<String>();
        parameters.add(String.valueOf(user.getId()));
        parameters.add(cardId);
        parameters.add(amount);
        parameters.add(code);
        parameters.add(number);
        try {
            logic.action(parameters);
            ReplenishBalanceLogic balanceLogic = (ReplenishBalanceLogic) logic;
            request.getSession().setAttribute(Constants.ERROR, PageError.getError(Constants.FALSE, ""));
            request.getSession().setAttribute(Constants.SESSION_USER, balanceLogic.getUser());
            router.setRoutePath(RoutePath.REDIRECT_USER_HOME.getRoutePath());
            logger.info("Succesfully replenish card balance!");
        } catch (LogicException ex) {
            logger.error(ex);
            request.setAttribute(Constants.ERROR,
                    PageError.getError(Constants.TRUE, ex.getMessage()));
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
