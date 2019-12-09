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
import by.epam.project.logic.user.CreateCardLogic;
import by.epam.project.util.Constants;

public class CreateCardCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(CreateCardCommand.class);
    private Logic logic = new CreateCardLogic();

    /**
     * Command for creation credit card
     */
    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Creating credit card executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        String cardMaster = request.getParameter(Constants.CREATE_CARD_MASTER);
        String cardCode = request.getParameter(Constants.CREATE_CARD_CODE);
        int identificationNumber = Integer.parseInt(request.getParameter(Constants.CREATE_CARD_NUMBER));
        String validationDate = request.getParameter(Constants.CREATE_CARD_DATE);
        List<String> parameters = new ArrayList<>();
        parameters.add(cardMaster);
        parameters.add(cardCode);
        parameters.add(String.valueOf(identificationNumber));
        parameters.add(validationDate);
        parameters.add(String.valueOf(user.getId()));
        try {
            logic.action(parameters);
            router.setRoutePath(RoutePath.REDIRECT_USER_BILLING.getRoutePath());
            logger.info("Succesfully creating credit card executing!");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR, ex.getMessage());
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
