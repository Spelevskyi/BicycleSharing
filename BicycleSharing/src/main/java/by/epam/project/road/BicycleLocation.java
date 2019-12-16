package by.epam.project.road;

import java.math.BigDecimal;
import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.dao.impl.BicycleDaoImpl;
import by.epam.project.dao.impl.DebtDaoImpl;
import by.epam.project.dao.impl.OrderDaoImpl;
import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.billing.PriceList;
import by.epam.project.entity.debt.Debt;
import by.epam.project.entity.order.OrderStatus;
import by.epam.project.entity.order.RentalOrder;
import by.epam.project.entity.point.RentalPoint;
import by.epam.project.entity.user.User;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.LogicException;
import by.epam.project.road.paying.BillingOperation;
import by.epam.project.util.Constants;
import by.epam.project.util.CurrentDate;

public class BicycleLocation {

    private static final Logger logger = LogManager.getLogger(BicycleLocation.class);

    /**
     * Static method for changing operations for several entities after road ending
     * 
     * @param id - user id
     * @throws LogicException
     */
    public static void changeBicycleLocation(int id) throws LogicException {
        try {
            logger.info("Chnaging perameters after ending moving.");
            String dateTime = CurrentDate.getCurrentDate();
            OrderDaoImpl orderDao = new OrderDaoImpl();
            BicycleDaoImpl dao = new BicycleDaoImpl();
            DebtDaoImpl debtDao = new DebtDaoImpl();
            RentalOrder order = orderDao.findByRenterId(id).get();
            Bicycle bicycle = order.getBicycle();
            User user = order.getUser();
            RentalPoint point = bicycle.getPoint();
            PriceList list = bicycle.getPriceList();
            double distance = (bicycle.getSpeed() * TimeDifference.roadTime(order.getActualStartTime(), dateTime));
            BigDecimal price = BillingOperation.ChangeBalance(order, user, list);
            BigDecimal difference = price.subtract(user.getCash());
            if (difference.doubleValue() >= 0) {
                logger.info("User balance less than price.");
                Date creationDate = new Date(System.currentTimeMillis());
                user.setCash(BigDecimal.valueOf(0));
                user.setStatus(Constants.LOCKED);
                Debt debt = new Debt(id, difference, creationDate);
                debtDao.create(debt);
            }
            else {
                logger.info("User balance bigger than road price.");
                BigDecimal newCash = user.getCash().subtract(price);
                user.setCash(newCash);
                user.setStatus(Constants.UNLOCKED);
            }
            user.setOnRoad(false);
            order.setDistance(distance);
            order.setActualEndTime(dateTime);
            order.setBookedEndTime(dateTime);
            order.setStatus(OrderStatus.EXPIRE);
            bicycle.setStatus(Constants.ENABLE);
            bicycle.setOnRoad(false);
            switch (order.getDirection()) {
            case "North":
                logger.info("Moving to the North.");
                point.setY_coordinate(point.getY_coordinate() - (int) distance);
                dao.updateAfterMoving(order, bicycle, point, user);
                break;
            case "South":
                logger.info("Moving to the South.");
                point.setY_coordinate(point.getY_coordinate() + (int) distance);
                dao.updateAfterMoving(order, bicycle, point, user);
                break;
            case "West":
                logger.info("Moving to the West.");
                point.setX_coordinate(point.getX_coordinate() - (int) distance);
                dao.updateAfterMoving(order, bicycle, point, user);
                break;
            case "East":
                logger.info("Moving to the East.");
                point.setX_coordinate(point.getX_coordinate() + (int) distance);
                dao.updateAfterMoving(order, bicycle, point, user);
                break;
            case "North-West":
                logger.info("Moving to the North-West.");
                point.setX_coordinate(point.getX_coordinate() - (int) distance);
                point.setY_coordinate(point.getY_coordinate() - (int) distance);
                dao.updateAfterMoving(order, bicycle, point, user);
                break;
            case "North-East":
                logger.info("Moving to the North-East.");
                point.setX_coordinate(point.getX_coordinate() + (int) distance);
                point.setY_coordinate(point.getY_coordinate() - (int) distance);
                dao.updateAfterMoving(order, bicycle, point, user);
                break;
            case "South-West":
                logger.info("Moving to the South-West.");
                point.setX_coordinate(point.getX_coordinate() - (int) distance);
                point.setY_coordinate(point.getY_coordinate() + (int) distance);
                dao.updateAfterMoving(order, bicycle, point, user);
                break;
            case "South-East":
                logger.info("Moving to the South-East.");
                point.setX_coordinate(point.getX_coordinate() + (int) distance);
                point.setY_coordinate(point.getY_coordinate() + (int) distance);
                dao.updateAfterMoving(order, bicycle, point, user);
                break;
            default:
                break;
            }
        } catch (DaoException ex) {
            logger.error(ex);
            throw new LogicException("Ending current road failed!", ex);
        }
    }
}
