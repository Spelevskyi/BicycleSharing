package by.epam.project.logic.user;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import by.epam.project.dao.impl.BicycleDaoImpl;
import by.epam.project.dao.impl.OrderDaoImpl;
import by.epam.project.dao.impl.UserDaoImpl;
import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.order.RentalOrder;
import by.epam.project.entity.point.RentalPoint;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.util.Constants;

public class RentalPointsLogic implements Logic {

    private static final Logger logger = LogManager.getLogger(RentalPointsLogic.class);

    private BicycleDaoImpl bicycleDao = new BicycleDaoImpl();
    private UserDaoImpl userDao = new UserDaoImpl();
    private OrderDaoImpl orderDao = new OrderDaoImpl();

    private JsonArray pointsJson;
    private User user;
    private RentalOrder activeOrder;

    @Override
    public void action(List<String> parameters) throws LogicException {
        logger.info("Action of forwarding to user rental points page performing.");
        if (parameters.size() != Constants.RENTAL_POINTS_PARAMETERS_AMOUNT) {
            logger.error("Invalid user rental points parameters amount!");
            throw new LogicException("Invalid user rental points parameters amount!");
        }
        int userId = Integer.valueOf(parameters.get(0));
        try {
            Optional<RentalOrder> findedOrder = orderDao.findActiveOrder(userId);
            if (!findedOrder.isPresent()) {
                activeOrder = null;
            } else {
                activeOrder = findedOrder.get();
            }
            Map<Bicycle, RentalPoint> bicycles = bicycleDao.findBicyclesWithLocation();
            pointsJson = new JsonArray();
            for (Map.Entry<Bicycle, RentalPoint> bicycle : bicycles.entrySet()) {
                JsonObject object = new JsonObject();
                object.addProperty(Constants.JSON_ID, bicycle.getKey().getId());
                object.addProperty(Constants.JSON_IMAGE_PATH, bicycle.getKey().getImagePath());
                object.addProperty(Constants.JSON_BRAND, bicycle.getKey().getBrand().toString());
                object.addProperty(Constants.JSON_COLOR, bicycle.getKey().getColor().toString());
                object.addProperty(Constants.JSON_STATE, bicycle.getKey().getState().toString());
                object.addProperty(Constants.JSON_STAY_PRICE, bicycle.getKey().getPriceList().getStayPrice());
                object.addProperty(Constants.JSON_PER_MINUTE, bicycle.getKey().getPriceList().getPerMinutePrice());
                object.addProperty(Constants.JSON_PER_HOUR, bicycle.getKey().getPriceList().getPerHourPrice());
                object.addProperty(Constants.JSON_X_COORDINATE, bicycle.getValue().getX_coordinate());
                object.addProperty(Constants.JSON_Y_COORDINATE, bicycle.getValue().getY_coordinate());
                pointsJson.add(object);
            }
            user = userDao.findById(userId).get();
            logger.info("Succesfully forwarding to rental points page!");
        } catch (DaoException ex) {
            System.out.println(ex.getMessage());
            throw new LogicException("Forwarding to rental points page failed!", ex);
        }
    }

    public JsonArray getPointsJson() {
        return pointsJson;
    }

    public User getUser() {
        return user;
    }

    public RentalOrder getActiveOrder() {
        return activeOrder;
    }
}
