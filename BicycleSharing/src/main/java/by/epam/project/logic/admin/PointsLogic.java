package by.epam.project.logic.admin;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import by.epam.project.dao.impl.BicycleDaoImpl;
import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.point.RentalPoint;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;

public class PointsLogic implements Logic {

    public static final Logger logger = LogManager.getLogger(PointsLogic.class);
    private BicycleDaoImpl bicycleDao = new BicycleDaoImpl();

    private JsonArray pointsJson = new JsonArray();
    private Map<Bicycle, RentalPoint> bicycles;

    /**
     * Logic method of forwarding to admin points page
     */
    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of forwarding to points page executing.");
        if (parameters.size() != Constants.POINTS_PARAMETERS_AMOUNT) {
            logger.error("Invalid rental points page parameters amount!");
            throw new LogicException("Invalid rental points page parameters amount!");
        }
        try {
            Map<Bicycle, RentalPoint> points = bicycleDao.findBicyclesWithLocation();
            pointsJson = new JsonArray();
            for (Map.Entry<Bicycle, RentalPoint> point : points.entrySet()) {
                JsonObject object = new JsonObject();
                object.addProperty(Constants.X_COORDINATE, point.getValue().getX_coordinate());
                object.addProperty(Constants.Y_COORDINATE, point.getValue().getY_coordinate());
                pointsJson.add(object);
            }
            bicycles = bicycleDao.findBicyclesWithPoints();
            logger.info("Succefull rental points forwarding!");
        } catch (DaoException ex) {
            throw new LogicException("Forwarding to rental points page failed!", ex);
        }
    }

    public JsonArray getPointsJson() {
        return pointsJson;
    }

    public Map<Bicycle, RentalPoint> getBicycles() {
        return bicycles;
    }

}
