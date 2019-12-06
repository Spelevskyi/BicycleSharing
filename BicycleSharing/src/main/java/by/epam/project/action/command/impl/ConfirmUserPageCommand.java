package by.epam.project.action.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.project.action.command.ActionCommand;
import by.epam.project.action.command.RoutePath;
import by.epam.project.action.command.RouteType;
import by.epam.project.action.command.Router;
import by.epam.project.util.Constants;

public class ConfirmUserPageCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String email = request.getParameter(Constants.EMAIL);
        request.getSession().setAttribute(Constants.EMAIL, email);
        router.setPagePath(RoutePath.CONFIRMATION_PAGE_PATH.getPagePath());
        router.setType(RouteType.REDIRECT);
        return router;
    }
}
