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
import by.epam.project.logic.user.EndMoveLogic;
import by.epam.project.util.Constants;
import by.epam.project.util.PageError;

public class EndMoveCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(EndMoveCommand.class);
    private Logic logic = new EndMoveLogic();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Ending road executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        List<String> parameters = new ArrayList<>();
        parameters.add(String.valueOf(user.getId()));
        try {
            logic.action(parameters);
            EndMoveLogic endLogic = (EndMoveLogic) logic;
            request.getSession().setAttribute(Constants.SESSION_USER, endLogic.getUser());
            router.setRoutePath(RoutePath.REDIRECT_USER_POINTS.getRoutePath());
            logger.info("Succesfully ending current road!");
        } catch (LogicException ex) {
            logger.info(ex);
            request.getSession().setAttribute(Constants.ERROR, PageError.getError(Constants.TRUE, ex.getMessage()));
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }


}
