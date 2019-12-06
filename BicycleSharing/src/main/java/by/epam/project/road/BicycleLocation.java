package by.epam.project.road;

import java.math.BigDecimal;
import java.sql.Date;

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

    public static void changeBicycleLocation(int id) throws LogicException {
        try {
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
            System.out.println(price.doubleValue() + " " + "df");
            BigDecimal difference = price.subtract(user.getCash());
            if (difference.doubleValue() >= 0) {
                Date creationDate = new Date(System.currentTimeMillis());
                user.setCash(BigDecimal.valueOf(0));
                user.setStatus(Constants.UNLOCKED);
                Debt debt = new Debt(id, difference, creationDate);
                debtDao.create(debt);
            }
            else {
                BigDecimal newCash = user.getCash().subtract(price);
                user.setCash(newCash);
                user.setStatus(Constants.LOCKED);
            }
            order.setDistance(distance);
            order.setActualEndTime(dateTime);
            order.setBookedEndTime(dateTime);
            order.setStatus(OrderStatus.EXPIRE);
            bicycle.setStatus(Constants.ENABLE);
            switch (order.getDirection()) {
            case "North":
                point.setY_coordinate(point.getY_coordinate() - (int) distance);
                dao.updateAfterMoving(order, bicycle, point, user);
                break;
            case "South":
                point.setY_coordinate(point.getY_coordinate() + (int) distance);
                dao.updateAfterMoving(order, bicycle, point, user);
                break;
            case "West":
                point.setX_coordinate(point.getX_coordinate() - (int) distance);
                dao.updateAfterMoving(order, bicycle, point, user);
                break;
            case "East":
                point.setX_coordinate(point.getX_coordinate() + (int) distance);
                dao.updateAfterMoving(order, bicycle, point, user);
                break;
            case "North-West":
                point.setX_coordinate(point.getX_coordinate() - (int) distance);
                point.setY_coordinate(point.getY_coordinate() - (int) distance);
                dao.updateAfterMoving(order, bicycle, point, user);
                break;
            case "North-East":
                point.setX_coordinate(point.getX_coordinate() + (int) distance);
                point.setY_coordinate(point.getY_coordinate() - (int) distance);
                dao.updateAfterMoving(order, bicycle, point, user);
                break;
            case "South-West":
                point.setX_coordinate(point.getX_coordinate() - (int) distance);
                point.setY_coordinate(point.getY_coordinate() + (int) distance);
                dao.updateAfterMoving(order, bicycle, point, user);
                break;
            case "South-East":
                point.setX_coordinate(point.getX_coordinate() + (int) distance);
                point.setY_coordinate(point.getY_coordinate() + (int) distance);
                dao.updateAfterMoving(order, bicycle, point, user);
                break;
            default:
                break;
            }
            System.out.println(point.getX_coordinate() + " " + point.getY_coordinate());
        } catch (DaoException ex) {
            throw new LogicException("Ending current road failed!", ex);
        }
    }
}
