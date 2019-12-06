package by.epam.project.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.order.OrderStatus;
import by.epam.project.entity.order.RentalOrder;
import by.epam.project.exception.DaoException;
import by.epam.project.util.Constants;

public class RentalOrderBuilder {

    private static final Logger logger = LogManager.getLogger(RentalOrderBuilder.class);

    // RentalOrderBuilder method of creating new order entity from ResultSet as
    // parameter
    public static Optional<RentalOrder> createOrder(ResultSet result) throws DaoException {
        try {
            logger.info("Creating new rental order entity.");
            if (!result.next()) {
                logger.error("Active rental order not exists!");
                return Optional.empty();
            }
            result.beforeFirst();
            result.next();
            int bicycleId = Integer.valueOf(result.getString(Constants.ORDER_BICYCLE_ID));
            int renterId = Integer.valueOf(result.getString(Constants.ORDER_RENTER_ID));
            String bookedStartTime = result.getString(Constants.ORDER_BOOKED_START_TIME);
            String bookedEndTime = result.getString(Constants.ORDER_BOOKED_END_TIME);
            String actualStartTime = result.getString(Constants.ORDER_ACTUAL_START_TIME);
            String actualEndTime = result.getString(Constants.ORDER_ACTUAL_END_TIME);
            String status = result.getString(Constants.ORDER_STATUS);
            String direction = result.getString(Constants.ORDER_DIRECTION);
            int distance = result.getInt(Constants.ORDER_DISTANCE);
            RentalOrder order = new RentalOrder(bicycleId, renterId, bookedStartTime, bookedEndTime, actualStartTime,
                    actualEndTime, OrderStatus.valueOf(status), direction, distance);
            order.setId(Integer.valueOf(result.getInt(Constants.ORDER_ID)));
            logger.info("Rental order entity was created succesfully!");
            return Optional.of(order);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    // RentalOrderBuilder static method for creating Map with key - RentalOrder and
    // value - Bicycle
    public static Map<RentalOrder, Bicycle> createOrderWithBicycle(ResultSet result) throws DaoException {
        logger.info("Creating orders with bicycles in builder.");
        Map<RentalOrder, Bicycle> orders = new HashMap<>();
        try {
            while (result.next()) {
                int bicycleId = Integer.valueOf(result.getString(Constants.ORDER_BICYCLE_ID));
                int renterId = Integer.valueOf(result.getString(Constants.ORDER_RENTER_ID));
                String bookedStartTime = result.getString(Constants.ORDER_BOOKED_START_TIME);
                String bookedEndTime = result.getString(Constants.ORDER_BOOKED_END_TIME);
                String actualStartTime = result.getString(Constants.ORDER_ACTUAL_START_TIME);
                String actualEndTime = result.getString(Constants.ORDER_ACTUAL_END_TIME);
                String status = result.getString(Constants.ORDER_STATUS);
                String direction = result.getString(Constants.ORDER_DIRECTION);
                int distance = result.getInt(Constants.ORDER_DISTANCE);
                RentalOrder order = new RentalOrder(bicycleId, renterId, bookedStartTime, bookedEndTime,
                        actualStartTime, actualEndTime, OrderStatus.valueOf(status), direction, distance);
                order.setId(Integer.valueOf(result.getInt(Constants.ORDER_ID)));
                Bicycle bicycle = BicycleBuilder.createOrderBicycle(result).get();
                orders.put(order, bicycle);
                logger.info("New map element was added.");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        logger.info("Succesfully creating orders with bicycles.");
        return orders;
    }

    public static Optional<RentalOrder> createOrderWithBicycleAndUser(ResultSet result) throws DaoException {
        System.out.println("sdsda");
        try {
            logger.info("Creating new rental order entity.");
            if (!result.next()) {
                logger.error("Rental order not exists!");
                return Optional.empty();
            }
            int bicycleId = Integer.valueOf(result.getString(Constants.ORDER_BICYCLE_ID));
            int renterId = Integer.valueOf(result.getString(Constants.ORDER_RENTER_ID));
            String bookedStartTime = result.getString(Constants.ORDER_BOOKED_START_TIME);
            String bookedEndTime = result.getString(Constants.ORDER_BOOKED_END_TIME);
            String actualStartTime = result.getString(Constants.ORDER_ACTUAL_START_TIME);
            String actualEndTime = result.getString(Constants.ORDER_ACTUAL_END_TIME);
            String status = result.getString(Constants.ORDER_STATUS);
            String direction = result.getString(Constants.ORDER_DIRECTION);
            int distance = result.getInt(Constants.ORDER_DISTANCE);
            RentalOrder order = new RentalOrder(bicycleId, renterId, bookedStartTime, bookedEndTime, actualStartTime,
                    actualEndTime, OrderStatus.valueOf(status), direction, distance);
            order.setId(Integer.valueOf(result.getInt(Constants.ORDER_ID)));
            order.setUser(UserBuilder.createOrderUser(result).get());
            order.setBicycle(BicycleBuilder.createBicycleWithPointAndBilling(result).get());
            logger.info("Rental order entity was created succesfully!");
            return Optional.ofNullable(order);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    public static ArrayList<RentalOrder> createUserOrders(ResultSet result) throws DaoException {
        ArrayList<RentalOrder> orders = new ArrayList<>();
        try {
            while (result.next()) {
                int bicycleId = Integer.valueOf(result.getString(Constants.ORDER_BICYCLE_ID));
                int renterId = Integer.valueOf(result.getString(Constants.ORDER_RENTER_ID));
                String bookedStartTime = result.getString(Constants.ORDER_BOOKED_START_TIME);
                String bookedEndTime = result.getString(Constants.ORDER_BOOKED_END_TIME);
                String actualStartTime = result.getString(Constants.ORDER_ACTUAL_START_TIME);
                String actualEndTime = result.getString(Constants.ORDER_ACTUAL_END_TIME);
                String status = result.getString("rental_order.Status");
                String direction = result.getString(Constants.ORDER_DIRECTION);
                int distance = result.getInt(Constants.ORDER_DISTANCE);
                RentalOrder order = new RentalOrder(bicycleId, renterId, bookedStartTime, bookedEndTime,
                        actualStartTime, actualEndTime, OrderStatus.valueOf(status), direction, distance);
                order.setId(Integer.valueOf(result.getInt(Constants.ORDER_ID)));
                logger.info("Rental order entity was created succesfully!");
                orders.add(order);
            }
            logger.info("Rental order list was created succesfully!");
            return orders;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    public static ArrayList<RentalOrder> createOrders(ResultSet result) throws DaoException {
        ArrayList<RentalOrder> orders = new ArrayList<>();
        try {
            while (result.next()) {
                int bicycleId = Integer.valueOf(result.getString(Constants.ORDER_BICYCLE_ID));
                int renterId = Integer.valueOf(result.getString(Constants.ORDER_RENTER_ID));
                String bookedStartTime = result.getString(Constants.ORDER_BOOKED_START_TIME);
                String bookedEndTime = result.getString(Constants.ORDER_BOOKED_END_TIME);
                String actualStartTime = result.getString(Constants.ORDER_ACTUAL_START_TIME);
                String actualEndTime = result.getString(Constants.ORDER_ACTUAL_END_TIME);
                String status = result.getString("rental_order.Status");
                String direction = result.getString(Constants.ORDER_DIRECTION);
                int distance = result.getInt(Constants.ORDER_DISTANCE);
                RentalOrder order = new RentalOrder(bicycleId, renterId, bookedStartTime, bookedEndTime,
                        actualStartTime, actualEndTime, OrderStatus.valueOf(status), direction, distance);
                order.setId(Integer.valueOf(result.getInt(Constants.ORDER_ID)));
                logger.info("Rental order entity was created succesfully!");
                orders.add(order);
            }
            logger.info("Rental order list was created succesfully!");
            return orders;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }
}
