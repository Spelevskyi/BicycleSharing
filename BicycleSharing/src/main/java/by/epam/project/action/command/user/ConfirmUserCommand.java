package by.epam.project.action.command.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.action.command.ActionCommand;
import by.epam.project.action.command.Router;

public class ConfirmUserCommand implements ActionCommand {

    public static final Logger logger = LogManager.getLogger(ConfirmUserCommand.class);

  
    @Override
    public Router execute(HttpServletRequest request) {
       Router router = new Router();
        /*
         * router.setType(RouteType.REDIRECT); HttpSession session =
         * request.getSession(); String email = request.getParameter(Constants.EMAIL);
         * int enteredCode =
         * Integer.valueOf(request.getParameter(Constants.CONFIRMATION_CODE)); int
         * confirmationCode = (int) session.getAttribute(Constants.CONFIRMATION_CODE);
         * List<String> parameters = new ArrayList<>(); parameters.add(email);
         * parameters.add(String.valueOf(enteredCode));
         * parameters.add(String.valueOf(enteredCode));
         * router.setRoutePath(RoutePath.USER_MAIN_PAGE_PATH.getRoutePath()); try {
         * logic.action(parameters); request.getSession().setAttribute(Constants.ERROR,
         * PageError.getError(Constants.FALSE, ""));
         * logger.info("Successfully confirming email."); } catch (LogicException ex) {
         * logger.error(ex); request.getSession().setAttribute(Constants.ERROR,
         * PageError.getError(Constants.TRUE, ex.getMessage())); } if (confirmationCode
         * == enteredCode) { service.confirmUser(email);
         * session.removeAttribute(Constants.SESSION_USER);
         * session.removeAttribute(Constants.SESSION_IS_LOGIN);
         * session.removeAttribute(Constants.CONFIRMATION_CODE);
         * router.setPagePath(PagePath.INDEX_PAGE_PATH.getPagePath());
         * router.setType(RouteType.REDIRECT); } else {
         * router.setPagePath(PagePath.MAIN_PAGE_PATH.getPagePath());
         * router.setType(RouteType.FORWARD); }
         */
        return router;
    }
}
