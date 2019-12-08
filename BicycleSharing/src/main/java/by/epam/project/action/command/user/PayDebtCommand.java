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
import by.epam.project.logic.user.PayDebtLogic;
import by.epam.project.util.Constants;
import by.epam.project.util.PageError;

public class PayDebtCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(PayDebtCommand.class);

    private Logic logic = new PayDebtLogic();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Paying user debt executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        String debtId = request.getParameter(Constants.PAY_DEBT_ID);
        List<String> parameters = new ArrayList<>();
        parameters.add(debtId);
        parameters.add(String.valueOf(user.getId()));
        try {
            logic.action(parameters);
            PayDebtLogic payLogic = (PayDebtLogic) logic;
            request.getSession().setAttribute(Constants.SESSION_USER, payLogic.getUser());
            router.setRoutePath(RoutePath.REDIRECT_USER_BILLING.getRoutePath());
            logger.info("Succesfully pay debt executing!");
        } catch (LogicException ex) {
            logger.error(ex);
            request.setAttribute(Constants.ERROR, PageError.getError(Constants.TRUE, ex.getMessage()));
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
