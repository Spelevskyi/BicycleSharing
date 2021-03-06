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
import by.epam.project.entity.user.User;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.logic.admin.AdminHomeLogic;
import by.epam.project.util.Constants;

public class AdminHomeCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(AdminHomeCommand.class);

    private Logic logic = new AdminHomeLogic();

    /**
     * Command of forwarding to admin home page
     */
    public Router execute(HttpServletRequest request) {
        logger.info("Admin home page forwarding executing.");
        Router router = new Router();
        router.setType(RouteType.FORWARD);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        List<String> parameters = new ArrayList<String>();
        parameters.add(String.valueOf(user.getId()));
        try {
            logic.action(parameters);
            AdminHomeLogic homeLogic = (AdminHomeLogic) logic;
            request.setAttribute(Constants.DEBTS, homeLogic.getDebts());
            router.setRoutePath(RoutePath.ADMIN_MAIN_PAGE_PATH.getRoutePath());
            logger.info("Succesfully forwarding to admin home page.");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, ex.getMessage());
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
