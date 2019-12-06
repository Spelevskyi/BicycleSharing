package by.epam.project.action.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.project.action.command.ActionCommand;
import by.epam.project.action.command.RoutePath;
import by.epam.project.action.command.RouteType;
import by.epam.project.action.command.Router;
import by.epam.project.dao.impl.RentalPointDaoImpl;
import by.epam.project.entity.point.RentalPoint;

public class AddRentalPointCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        RentalPointDaoImpl service = new RentalPointDaoImpl();
        String x_coordinate = request.getParameter("X");
        String y_coordinate = request.getParameter("Y");
        System.out.println(x_coordinate);
        try {
            service.createRentalPoint(Integer.valueOf(x_coordinate), Integer.valueOf(y_coordinate));
            List<RentalPoint> points = service.findAll();
            request.getSession().setAttribute("rentalPoints", points);
            router.setPagePath(RoutePath.POINTS_PAGE_PATH.getPagePath());
            router.setType(RouteType.REDIRECT);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            router.setPagePath(RoutePath.INDEX_PAGE_PATH.getPagePath());
        }
        return router;
    }
}
