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
import by.epam.project.logic.user.FavoritesLogic;
import by.epam.project.util.Constants;

public class FavoritesPageCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(FavoritesPageCommand.class);
    private Logic logic = new FavoritesLogic();

    /**
     * Favorites page command forwarding
     */
    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Favorite bicycles page forwarding executing.");
        Router router = new Router();
        router.setType(RouteType.FORWARD);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        List<String> parameters = new ArrayList<>();
        parameters.add(String.valueOf(user.getId()));
        try {
            logic.action(parameters);
            FavoritesLogic favoritesLogic = (FavoritesLogic) logic;
            request.setAttribute(Constants.USER, favoritesLogic.getUser());
            request.setAttribute(Constants.BRAND_SORTED, favoritesLogic.getSortedByBrand());
            request.setAttribute(Constants.COLOR_SORTED, favoritesLogic.getSortedByColor());
            router.setRoutePath(RoutePath.FAVORITES_PAGE_PATH.getRoutePath());
            logger.info("Forwarding to favorites bicycles page.");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, ex.getMessage());
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
