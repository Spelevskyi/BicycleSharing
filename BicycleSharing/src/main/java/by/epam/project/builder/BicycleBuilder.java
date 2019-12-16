package by.epam.project.builder;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.entity.bicycle.Bicycle;
import by.epam.project.entity.billing.PriceList;
import by.epam.project.entity.point.RentalPoint;
import by.epam.project.exception.DaoException;
import by.epam.project.util.Constants;

public class BicycleBuilder {

    private static final Logger logger = LogManager.getLogger(BicycleBuilder.class);

    /**
     * BicycleBuilder method for creating bicycle from ResultSet
     * 
     * @param result - ResultSet
     * @return Optional value of bicycle
     * @throws DaoException
     */
    public static Optional<Bicycle> createBicycle(ResultSet result) throws DaoException {
        logger.info("Creating bicycle in builder method.");
        try {
            if (!result.next()) {
                logger.info("Bicycle was not found in builder method by current result set!");
                return Optional.empty();
            }
            result.beforeFirst();
            result.next();
            String brand = result.getString(Constants.BICYCLE_BRAND);
            String color = result.getString(Constants.BICYCLE_COLOR);
            int maxSpeed = result.getInt(Constants.BICYCLE_MAX_SPEED);
            Date registrationDate = result.getDate(Constants.BICYCLE_REGISTRATION_DATE);
            String state = result.getString(Constants.BICYCLE_STATE);
            String image_path = result.getString(Constants.BICYCLE_IMAGE_PATH);
            String status = result.getString(Constants.BICYCLE_STATUS);
            Bicycle bicycle = new Bicycle(brand, color, maxSpeed, registrationDate, state, image_path, status);
            bicycle.setId(result.getInt(Constants.BICYCLE_ID));
            bicycle.setBillingId(result.getInt(Constants.BICYCLE_BILLING_ID));
            bicycle.setOnRoad(result.getBoolean(Constants.BICYCLE_ON_ROAD));
            return Optional.of(bicycle);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    /**
     * BicycleBuilder method for creating bicycle for order
     * 
     * @param result - ResultSet
     * @return Optional value of bicycle
     * @throws DaoException
     */
    public static Optional<Bicycle> createOrderBicycle(ResultSet result) throws DaoException {
        logger.info("Creating bicycle in builder method.");
        try {
            String brand = result.getString(Constants.BICYCLE_BRAND);
            String color = result.getString(Constants.BICYCLE_COLOR);
            int maxSpeed = result.getInt(Constants.BICYCLE_MAX_SPEED);
            Date registrationDate = result.getDate(Constants.BICYCLE_REGISTRATION_DATE);
            String state = result.getString(Constants.BICYCLE_STATE);
            String image_path = result.getString(Constants.BICYCLE_IMAGE_PATH);
            String status = result.getString(Constants.BICYCLE_STATUS);
            Bicycle bicycle = new Bicycle(brand, color, maxSpeed, registrationDate, state, image_path, status);
            bicycle.setId(result.getInt(Constants.BICYCLE_ID));
            bicycle.setBillingId(result.getInt(Constants.BICYCLE_BILLING_ID));
            bicycle.setOnRoad(result.getBoolean(Constants.BICYCLE_ON_ROAD));
            logger.info("Succesfully creating bicycle in builder method!");
            return Optional.of(bicycle);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    /**
     * BicycleBuilder method for creating Bicycle with billing and point
     * 
     * @param result - ResultSet
     * @return Optional value of bicycle with point and price list as parameters of
     *         entity
     * @throws DaoException
     */
    public static Optional<Bicycle> createBicycleWithPointAndBilling(ResultSet result) throws DaoException {
        logger.info("Creating bicycle with point and price list in builder method.");
        try {
            result.beforeFirst();
            result.next();
            String brand = result.getString(Constants.BICYCLE_BRAND);
            String color = result.getString(Constants.BICYCLE_COLOR);
            int maxSpeed = result.getInt(Constants.BICYCLE_MAX_SPEED);
            Date registrationDate = result.getDate(Constants.BICYCLE_REGISTRATION_DATE);
            String state = result.getString(Constants.BICYCLE_STATE);
            String image_path = result.getString(Constants.BICYCLE_IMAGE_PATH);
            String status = result.getString(Constants.BICYCLE_STATUS);
            Bicycle bicycle = new Bicycle(brand, color, maxSpeed, registrationDate, state, image_path, status);
            bicycle.setId(result.getInt(Constants.BICYCLE_ID));
            bicycle.setBillingId(result.getInt(Constants.BICYCLE_BILLING_ID));
            RentalPoint point = new RentalPoint(result.getInt(Constants.POINT_X_COORDINATE),
                    result.getInt(Constants.POINT_Y_COORDINATE));
            point.setId(result.getInt(Constants.POINT_ID));
            point.setBicycleId(result.getInt(Constants.POINT_BICYCLE_ID));
            bicycle.setOnRoad(result.getBoolean(Constants.BICYCLE_ON_ROAD));
            bicycle.setPoint(point);
            bicycle.setPriceList(PriceListBuilder.createPriceList(result).get());
            logger.info("Succesfully creating bicycle with point and price list in builder method!");
            return Optional.of(bicycle);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    /**
     * BicycleBuilder method for creating bicycles with location
     * 
     * @param result - ResultSet
     * @return Map with key - Bicycle and value - point
     * @throws DaoException
     */
    public static Map<Bicycle, RentalPoint> createBicyclesWithLocation(ResultSet result) throws DaoException {
        logger.info("Creating orders with bicycles in builder.");
        Map<Bicycle, RentalPoint> bicycles = new HashMap<>();
        try {
            while (result.next()) {
                String brand = result.getString(Constants.BICYCLE_BRAND);
                String color = result.getString(Constants.BICYCLE_COLOR);
                int maxSpeed = result.getInt(Constants.BICYCLE_MAX_SPEED);
                Date registrationDate = result.getDate(Constants.BICYCLE_REGISTRATION_DATE);
                String state = result.getString(Constants.BICYCLE_STATE);
                String image_path = result.getString(Constants.BICYCLE_IMAGE_PATH);
                String status = result.getString(Constants.BICYCLE_STATUS);
                Bicycle bicycle = new Bicycle(brand, color, maxSpeed, registrationDate, state, image_path, status);
                bicycle.setId(result.getInt(Constants.BICYCLE_ID));
                bicycle.setBillingId(result.getInt(Constants.BICYCLE_BILLING_ID));
                RentalPoint point = new RentalPoint(result.getInt(Constants.POINT_X_COORDINATE),
                        result.getInt(Constants.POINT_Y_COORDINATE));
                point.setId(result.getInt(Constants.POINT_ID));
                point.setBicycleId(result.getInt(Constants.POINT_BICYCLE_ID));
                bicycle.setOnRoad(result.getBoolean(Constants.BICYCLE_ON_ROAD));
                PriceList list = PriceListBuilder.createBicyclePriceList(result).get();
                bicycle.setPriceList(list);
                bicycles.put(bicycle, point);
                logger.info("Bicycle was succesfully created in builder method!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        logger.info("Bicycles with location were created!");
        return bicycles;
    }

    /**
     * BicycleBuilder method for sorting favorites bicycles by brand
     * 
     * @param result - ResultSet
     * @return Map with key - Integer value of amount of rentals and value - bicycle
     *         brand
     * @throws DaoException
     */
    public static Map<Integer, String> createFavoritesByBrand(ResultSet result) throws DaoException {
        logger.info("Sotring favorites users bicycles by brand.");
        Map<Integer, String> favorites = new HashMap<>();
        try {
            while (result.next()) {
                String brand = result.getString(Constants.BICYCLE_BRAND);
                int amount = result.getInt(Constants.BICYCLE_COUNT);
                favorites.put(amount, brand);
                logger.info("Adding new map element after favorites brand sorting.");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        logger.info("Succesfully sorting favorites by brand!");
        return favorites;
    }

    /**
     * BicycleBuilder method for sorting favorites bicycles by color
     * 
     * @param result - ResultSet
     * @return Map with key - Integer value of amount of rentals and value - bicycle
     *         color
     * @throws DaoException
     */
    public static Map<Integer, String> createFavoritesByColor(ResultSet result) throws DaoException {
        logger.info("Sotring favorites users bicycles by color.");
        Map<Integer, String> favorites = new HashMap<>();
        try {
            while (result.next()) {
                String brand = result.getString(Constants.BICYCLE_COLOR);
                int amount = result.getInt(Constants.BICYCLE_COUNT);
                favorites.put(amount, brand);
                logger.info("Adding new map element after favorites color sorting.");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        logger.info("Succesfully sorting favorites by color!");
        return favorites;
    }

    /**
     * BicycleBuilder method for creation bicycles
     * 
     * @param result - ResultSet
     * @return list of bicycles
     * @throws DaoException
     */
    public static ArrayList<Bicycle> createBicycles(ResultSet result) throws DaoException {
        logger.info("Creating bicycles in builder.");
        ArrayList<Bicycle> bicycles = new ArrayList<>();
        try {
            if (!result.next()) {
                logger.error("Bicycles were not found!");
            }
            result.beforeFirst();
            while (result.next()) {
                String brand = result.getString(Constants.BICYCLE_BRAND);
                String color = result.getString(Constants.BICYCLE_COLOR);
                int maxSpeed = result.getInt(Constants.BICYCLE_MAX_SPEED);
                Date registrationDate = result.getDate(Constants.BICYCLE_REGISTRATION_DATE);
                String state = result.getString(Constants.BICYCLE_STATE);
                String image_path = result.getString(Constants.BICYCLE_IMAGE_PATH);
                String status = result.getString(Constants.BICYCLE_STATUS);
                Bicycle bicycle = new Bicycle(brand, color, maxSpeed, registrationDate, state, image_path, status);
                bicycle.setId(result.getInt(Constants.BICYCLE_ID));
                bicycle.setBillingId(result.getInt(Constants.BICYCLE_BILLING_ID));
                bicycle.setOnRoad(result.getBoolean(Constants.BICYCLE_ON_ROAD));
                bicycles.add(bicycle);
                logger.info("Bicycle was created!");
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        logger.info("All bicycles were created!");
        return bicycles;
    }
}
