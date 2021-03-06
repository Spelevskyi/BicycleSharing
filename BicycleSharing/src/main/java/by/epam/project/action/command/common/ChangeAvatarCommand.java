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
import by.epam.project.logic.common.ChangeAvatarLogic;
import by.epam.project.util.Constants;

public class ChangeAvatarCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(ChangeAvatarCommand.class);

    private Logic logic = new ChangeAvatarLogic();

    /**
     * Command for changing user avatar
     */
    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Avatar image changing executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        String imagePath = request.getParameter(Constants.ACCOUNT_IMAGE_PATH);
        List<String> parameters = new ArrayList<>();
        parameters.add(imagePath);
        parameters.add(String.valueOf(user.getId()));
        try {
            logic.action(parameters);
            ChangeAvatarLogic avatarLogic = (ChangeAvatarLogic) logic;
            request.getSession().setAttribute(Constants.SESSION_USER, avatarLogic.getUser());
            router.setRoutePath(RoutePath.REDIRECT_ACCOUNT_PAGE.getRoutePath());
            logger.info("Succesfully changing avatar image executing!");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, ex.getMessage());
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}

