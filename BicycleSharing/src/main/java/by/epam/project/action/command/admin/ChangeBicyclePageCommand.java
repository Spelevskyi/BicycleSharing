package by.epam.project.action.command.admin;

import javax.servlet.http.HttpServletRequest;

import by.epam.project.action.command.ActionCommand;
import by.epam.project.action.command.Router;

public class ChangeBicyclePageCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        /*
         * router.setPagePath(PagePath.CHANGE_PROFILE_PAGE_PATH.getPagePath());
         * router.setType(RouteType.REDIRECT);
         */
        return router;
    }

}
