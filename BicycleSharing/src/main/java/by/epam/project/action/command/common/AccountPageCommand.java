package by.epam.project.action.command.common;

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
import by.epam.project.logic.common.AccountPageLogic;
import by.epam.project.util.Constants;

public class AccountPageCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(AccountPageCommand.class);

    private Logic logic = new AccountPageLogic();

    /**
     * Command of forwarding to account page
     */
    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Account page forwarding executing.");
        Router router = new Router();
        router.setType(RouteType.FORWARD);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        List<String> parameters = new ArrayList<>();
        parameters.add(String.valueOf(user.getId()));
        try {
            logic.action(parameters);
            AccountPageLogic accountLogic = (AccountPageLogic) logic;
            request.setAttribute(Constants.USER, accountLogic.getUser());
            router.setRoutePath(RoutePath.ACCOUNT_PAGE_PATH.getRoutePath());
            logger.info("Succesfully forwarding to account page!");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, ex.getMessage());
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
